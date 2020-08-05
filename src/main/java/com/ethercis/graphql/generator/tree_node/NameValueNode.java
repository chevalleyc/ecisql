package com.ethercis.graphql.generator.tree_node;

import com.ethercis.graphql.generator.AttributeNode;
import com.ethercis.graphql.generator.CamelToUpperSnake;
import com.ethercis.graphql.generator.ExemplifyMode;
import com.ethercis.nary.NaryNode;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;

/**
 * Created by christian on 5/25/2017.
 */
public class NameValueNode {

    ExemplifyMode tag_mode;
    Class nameValueClass;

    public NameValueNode(ExemplifyMode tag_mode, Class nameValueClass) {
        this.tag_mode = tag_mode;
        this.nameValueClass = nameValueClass;
    }

    public NaryNode node() throws ClassNotFoundException {
        String upperSnake = new CamelToUpperSnake(nameValueClass.getSimpleName()).toString();

        String onClause = "... on " + upperSnake;
        NaryNode naryNode = new NaryNode(onClause);
        naryNode.addChild(new AttributeNode(nameValueClass, tag_mode).node());
        if (nameValueClass.equals(DvText.class))
            naryNode.addSibling(new NameValueNode(tag_mode, DvCodedText.class).node());

        return naryNode;
    }
}
