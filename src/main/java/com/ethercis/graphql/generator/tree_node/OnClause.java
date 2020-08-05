package com.ethercis.graphql.generator.tree_node;

import com.ethercis.graphql.generator.CamelToUpperSnake;
import org.openehr.rm.composition.content.ContentItem;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;

/**
 * Created by christian on 5/25/2017.
 */
public class OnClause {

    String simpleClassName;

    public OnClause(ContentItem dataStructure) {
        this.simpleClassName = dataStructure.getClass().getSimpleName();
    }

    public OnClause(Class rmClass) {
        this.simpleClassName = rmClass.getSimpleName();
    }

    public OnClause(ItemStructure itemStructure) {
        this.simpleClassName = itemStructure.getClass().getSimpleName();
    }

    public String toString(){
        String onClause = "... on " + new CamelToUpperSnake(simpleClassName).toString();
        return onClause;
    }
}
