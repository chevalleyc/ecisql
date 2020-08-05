package com.ethercis.graphql.datastructure.arguments;

import com.ethercis.ehr.encode.wrappers.element.ElementWrapper;
import com.ethercis.graphql.datastructure.arguments.ArchetypeNodeIdArgument;
import com.ethercis.graphql.datastructure.arguments.NodeNameArgument;
import com.ethercis.graphql.datastructure.interfaces.Arguments;
import org.openehr.rm.composition.content.ContentItem;
import org.openehr.rm.datastructure.itemstructure.representation.Item;

import java.util.List;
import java.util.Map;

/**
 * Created by christian on 4/11/2017.
 */
public class NodePredicate {

    Object archetypeNodeIdFilter = null;
    Object nodeNameFilter = null;

    public NodePredicate(Map<String, Object> arguments) {
        if (!new Arguments(arguments).isNull(ArchetypeNodeIdArgument.getField()))
            archetypeNodeIdFilter = arguments.get(ArchetypeNodeIdArgument.getField());
        if (!new Arguments(arguments).isNull(NodeNameArgument.getField()))
            nodeNameFilter = arguments.get(NodeNameArgument.getField());
    }

    private boolean matchSingleton(String filter, String archetypeNodeId) {
        if (archetypeNodeId == null)
            return true;
        return filter.equals(archetypeNodeId);
    }

    public boolean match(String nodeName, String archetypeNodeId) {
        if (archetypeNodeId != null) {
            if (archetypeNodeIdFilter instanceof List) {
                boolean match = false;
                for (String filter : (List<String>) archetypeNodeIdFilter) {
                    match = match | matchSingleton(filter, archetypeNodeId);
                }
                if (!match)
                    return match;
            }
        }
        if (nodeName != null) {
            if (nodeNameFilter instanceof List) {
                boolean match = false;
                for (String filter : (List<String>) nodeNameFilter) {
                    match = match | matchSingleton(filter, nodeName);
                }
                if (!match)
                    return match;
            }
        }
        return true;
    }

    public boolean match(ContentItem contentItem){
        return match(contentItem.getName().getValue(), contentItem.getArchetypeNodeId());
    }

    public boolean match(Item item){
        return match(item.getName().getValue(), item.getArchetypeNodeId());
    }

    public boolean match(ElementWrapper item){
        return match(item.getName().getValue(), item.getArchetypeNodeId());
    }
}
