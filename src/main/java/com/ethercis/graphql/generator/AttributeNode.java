package com.ethercis.graphql.generator;

import com.ethercis.nary.NaryNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.composition.content.navigation.Section;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by christian on 4/13/2017.
 */
public class AttributeNode {

    protected static Logger log = LogManager.getLogger(AttributeNode.class);

    private Class rmClass;
    protected final ExemplifyMode tag_mode;

    private static Deque<String> visitStack = null;

    public AttributeNode(Class rmClass, ExemplifyMode tag_mode) {
        this.rmClass = rmClass;
        this.tag_mode = tag_mode;
        if (visitStack == null) {
            visitStack = new ArrayDeque<>();
        }
        visitStack.push(rmClass.getSimpleName());
    }

    public NaryNode<String> node() throws ClassNotFoundException {

        NaryNode<String> naryNode = null;

        //get the full constructor (@FullConstructor)

        for (Constructor constructor : rmClass.getConstructors()) {
            if (constructor.getAnnotation(FullConstructor.class) != null) {
                //get the parameter annotations
                for (int i = 0; i < constructor.getParameterCount(); i++) {
                    Annotation[] annotations = constructor.getParameterAnnotations()[i];
                    String parameterClassString = constructor.getGenericParameterTypes()[i].getTypeName();

                    if (constructor.getGenericParameterTypes()[i].getTypeName().equals("T"))
                        continue;

                    AttributeClass attributeClass = new AttributeClass(parameterClassString);
                    Class parameterClass = attributeClass.resolve();

                    if (new ItemStructureCheck(parameterClass).isStructural())
                        continue;

                    try {
                        Boolean required = ((Attribute) annotations[0]).required();
                        if (tag_mode.equals(ExemplifyMode.REQUIRED_FIELDS_ONLY) && !required)
                            continue;
                        String name = ((Attribute) annotations[0]).name();

                        //skip name field for Element...
                        if (    name.equals("accuracy")
                                || name.equals("charset")
                                || (rmClass.equals(Element.class) && name.equals("name"))
                                || (rmClass.equals(Section.class) && name.equals("items")))
                            continue;

                        NaryNode<String> addin = null;
                        if (!attributeClass.isJavaLang()) {
                            //check for recursive loop
                            if (visitStack.contains(parameterClass.getSimpleName())) {
                                continue;
                            }
                            addin = new AttributeNode(parameterClass, tag_mode).node();
                        }

                        if (naryNode == null) {
                            if (!attributeClass.isJavaLang()) {
                                if (addin != null) {
                                    naryNode = new NaryNode<>(new CamelToSnake(name).toString());
                                    naryNode.addChild(addin);
                                }
                            } else {
                                naryNode = new NaryNode<>(new CamelToSnake(name).toString());
                            }
                        } else {
                            if (!attributeClass.isJavaLang()) {
                                if (addin != null) {
                                    NaryNode sibling = naryNode.addSibling(new CamelToSnake(name).toString());
                                    sibling.addChild(addin);
                                }
                            } else {
                                NaryNode sibling = naryNode.addSibling(new CamelToSnake(name).toString());
                            }
                        }
                    } catch (Exception e) {
                        ;
                    }
                }
            }
        }

        if (naryNode == null) {
            log.warn("Null narynode...");
        }
        visitStack.pop();
        return naryNode;
    }
}
