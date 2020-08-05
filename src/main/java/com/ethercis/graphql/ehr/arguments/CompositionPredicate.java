package com.ethercis.graphql.ehr.arguments;

import com.ethercis.ehr.encode.wrappers.element.ElementWrapper;
import com.ethercis.graphql.datastructure.arguments.ArchetypeNodeIdArgument;
import com.ethercis.graphql.datastructure.arguments.NodeNameArgument;
import com.ethercis.graphql.datastructure.interfaces.Arguments;
import org.openehr.rm.composition.Composition;
import org.openehr.rm.composition.content.ContentItem;
import org.openehr.rm.datastructure.itemstructure.representation.Item;

import java.util.List;
import java.util.Map;

/**
 * Created by christian on 4/11/2017.
 */
public class CompositionPredicate {

    Object compositionIdFilter = null;

    public CompositionPredicate(Map<String, Object> arguments) {
        if (!new Arguments(arguments).isNull(ArchetypeNodeIdArgument.getField()))
            compositionIdFilter = arguments.get(ArchetypeNodeIdArgument.getField());
    }

    private boolean matchSingleton(String filter, String compositionUid) {
        if (compositionUid == null)
            return true;
        return filter.equals(compositionUid);
    }

    public boolean match(Composition composition) {
        if (composition != null) {
            if (compositionIdFilter instanceof List) {
                boolean match = false;
                for (String filter : (List<String>) compositionIdFilter) {
                    match = match | matchSingleton(filter, composition.getUid().getValue());
                }
                if (!match)
                    return match;
            }
        }
        return true;
    }

    public boolean match(String compositionId) {
        if (compositionId != null) {
            if (compositionIdFilter instanceof List) {
                boolean match = false;
                for (String filter : (List<String>) compositionIdFilter) {
                    match = match | matchSingleton(filter, compositionId);
                }
                if (!match)
                    return match;
            }
        }
        return true;
    }

}
