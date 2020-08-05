package com.ethercis.graphql.generator;

import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.composition.content.ContentItem;
import org.openehr.rm.datastructure.DataStructure;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datastructure.itemstructure.representation.Item;

/**
 * Created by christian on 5/24/2017.
 */
public class FieldAlias {
    String tag;
    String archetypeNodeId;
    String nodeName;

    public FieldAlias(String tag, String archetypeNodeId, String nodeName) {
        this.tag = tag;
        this.archetypeNodeId = archetypeNodeId;
        this.nodeName = nodeName;
    }

    public FieldAlias(String tag, DataStructure dataStructure){
        this.tag = tag;
        this.archetypeNodeId = dataStructure.getArchetypeNodeId();
        this.nodeName = dataStructure.getName().getValue();

    }

    public FieldAlias(String tag, Locatable locatable){
        this.tag = tag;
        this.archetypeNodeId = locatable.getArchetypeNodeId();
        this.nodeName = locatable.getName().getValue();
    }

    public FieldAlias(String tag, Item item){
        this.tag = tag;
        this.archetypeNodeId = item.getArchetypeNodeId();
        this.nodeName = item.getName().getValue();
    }

    public FieldAlias(String tag, ItemStructure item){
        this.tag = tag;
        this.archetypeNodeId = item.getArchetypeNodeId();
        this.nodeName = item.getName().getValue();
    }

    public FieldAlias(String tag, ContentItem item){
        this.tag = tag;
        this.archetypeNodeId = item.getArchetypeNodeId();
        this.nodeName = item.getName().getValue();
    }

    private String qualifyFieldAlias() {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(new ValidGraphQLName(archetypeNodeId));

        if (!tag.equals(Exemplify.TAG_ITEMS)) {
            stringBuffer.append("_");
            stringBuffer.append(new ValidGraphQLName(nodeName).toString().toLowerCase());
        }
        stringBuffer.append(":");
        //add argument examples
        stringBuffer.append(" ");
        stringBuffer.append(tag);
        stringBuffer.append("(archetype_node_id:");
        stringBuffer.append("\"");
        stringBuffer.append(archetypeNodeId);
        stringBuffer.append("\"");
        //TODO: should be an option
//        stringBuffer.append(", name_value:");
//        stringBuffer.append("\"");
//        stringBuffer.append(nodeName);
//        stringBuffer.append("\"");
        stringBuffer.append(")");
        return stringBuffer.toString();
    }


    public String alias() {
        return qualifyFieldAlias();
    }
}
