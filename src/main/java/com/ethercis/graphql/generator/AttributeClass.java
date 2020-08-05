package com.ethercis.graphql.generator;

import org.apache.commons.lang.ClassUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by christian on 4/15/2017.
 */
public class AttributeClass {

    protected static Logger log = LogManager.getLogger(AttributeClass.class);

    public static final String ORG_OPENEHR = "org.openehr";
    public static final String JAVA_LANG = "java.lang";
    public static final String JAVA = "java";
    public static final String JAVA_UTIL = "java.util";

    String parameterClassString;
    Class parameterClass;

    public AttributeClass(String parameterClassString) {
        this.parameterClassString = parameterClassString;
    }

    public Class resolve(){

        if (isRM()) { //Reference Model class
            try {
                parameterClass = Class.forName(parameterClassString);
            } catch (ClassNotFoundException cnfe) {
                //potentially an embedded class within a list, set, a generic etc.
                if (isRMGeneric()) {
                    if (isRMGeneric()) { //generic openEHR class (ex. DvInterval, ReferenceRange)
                        String embedded = parameterClassString.substring(parameterClassString.indexOf("org.openehr"), parameterClassString.indexOf("<"));
                        try {
                            parameterClass = Class.forName(embedded);
                        } catch (ClassNotFoundException e) {
                            log.error("0001-Unhandled class:" + parameterClassString);
                        }
                    } else if (isJavaUtilRMGeneric()) { //list, set etc.
                        String embedded = parameterClassString.substring(parameterClassString.indexOf("org.openehr"), parameterClassString.indexOf(">"));
                        try {
                            parameterClass = Class.forName(embedded);
                        } catch (ClassNotFoundException e) {
                            log.error("0002-Unhandled class:" + parameterClassString);
                        }
                    } else
                        log.error("0003-Could not handle class:" + parameterClassString);
                }
            }
        } else if (isJavaType()) {
            try {
                parameterClass =  Class.forName(parameterClassString);
            } catch (ClassNotFoundException e) {
                log.error("0004-Could not handle class:" + parameterClassString);
            }
        }
        else
            try {
                parameterClass = ClassUtils.getClass(parameterClassString);
            } catch (ClassNotFoundException e) {
                log.error("0005-Could not handle class:" + parameterClassString);
            }

        return parameterClass;
    }

    public Boolean isRM(){
        return parameterClassString.contains(ORG_OPENEHR);
    }

    public Boolean isRMGeneric(){
        return parameterClassString.contains(ORG_OPENEHR) && parameterClassString.contains("<") && !parameterClassString.contains(JAVA);
    }

    public Boolean isJavaType(){
        return parameterClassString.contains(JAVA_LANG);
    }

    public Boolean isJavaUtilRMGeneric(){
        return parameterClassString.contains(JAVA_UTIL) && isRMGeneric();
    }

    public Boolean isPrimitive(){
        return parameterClass.isPrimitive() ;
    }

    public Boolean isJavaLang(){
        return parameterClass.isPrimitive() || isJavaType();
    }
}
