package com.ethercis.graphql.generator.tree_node;

import com.ethercis.ehr.encode.wrappers.element.ElementWrapper;
import com.ethercis.graphql.generator.AttributeNode;
import com.ethercis.graphql.generator.CamelToUpperSnake;
import com.ethercis.graphql.generator.ExemplifyMode;
import com.ethercis.nary.NaryNode;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;

/**
 * Created by christian on 5/25/2017.
 */
public class DataValueNode {

    ExemplifyMode tag_mode;
    Class itemClass;

    public DataValueNode(ExemplifyMode tag_mode, Class dataValueClass) {
        this.tag_mode = tag_mode;
        itemClass = dataValueClass;
    }

    public DataValueNode(ExemplifyMode tag_mode, Element item) {
        this.tag_mode = tag_mode;
        itemClass = item.getValue().getClass();
    }

    public DataValueNode(ExemplifyMode tag_mode, ElementWrapper item) {
        this.tag_mode = tag_mode;
        itemClass = item.getAdaptedElement().getValue().getClass();
    }


    public NaryNode node() throws ClassNotFoundException {
        String upperSnake;

        if (itemClass.getSimpleName().equals("DvURI"))
            upperSnake = "DV_URI";
        else
            upperSnake = new CamelToUpperSnake(itemClass.getSimpleName()).toString();

        String onClause = "... on " + upperSnake;
        NaryNode naryNode = new NaryNode(onClause);
        naryNode.addChild(new AttributeNode(itemClass, tag_mode).node());
        if (itemClass.equals(DvText.class))
            naryNode.addSibling(new DataValueNode(tag_mode, DvCodedText.class).node());

        return naryNode;
    }
}
