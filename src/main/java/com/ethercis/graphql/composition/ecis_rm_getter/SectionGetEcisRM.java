package com.ethercis.graphql.composition.ecis_rm_getter;

import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.composition.SectionQLType;
import com.ethercis.graphql.datastructure.arguments.NodePredicate;
import com.ethercis.graphql.datastructure.interfaces.Arguments;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.composition.content.ContentItem;
import org.openehr.rm.composition.content.navigation.Section;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by christian on 4/12/2017.
 */
public class SectionGetEcisRM extends SectionQLType {

    public SectionGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher itemsFetcher = new DataFetcher<List<ContentItem>>() {
        @Override
        public List<ContentItem> get(DataFetchingEnvironment dataFetchingEnvironment) {
            List<ContentItem> contentItems = ((Section) dataFetchingEnvironment.getSource()).getItems();
            if (contentItems.size() == 0)
                return null;

            Map<String, Object> arguments = dataFetchingEnvironment.getArguments();
            //TODO: do some magic to retrieve the items depending on the predicates
            if (new Arguments(arguments).hasSetArguments()){
                List<ContentItem> filteredItems = new ArrayList<>();
                for (ContentItem item: contentItems){
                    if (!new NodePredicate(arguments).match(item))
                        continue;
                    filteredItems.add(item);
                }
                if (filteredItems.size() == 0)
                    contentItems =  null;
                else
                    contentItems = filteredItems;
            }
            return contentItems;
        }
//
//        return ((Section) dataFetchingEnvironment.getSource()).getItems();
//        }
    };

    @Override
    public GraphQLObjectType objectType() {
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .useDataFetcher(ITEMS, itemsFetcher)
                .getObjectType();
    }


}
