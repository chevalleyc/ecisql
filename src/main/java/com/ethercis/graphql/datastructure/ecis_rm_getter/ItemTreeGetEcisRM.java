package com.ethercis.graphql.datastructure.ecis_rm_getter;

import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datastructure.ItemTreeQLType;
import com.ethercis.graphql.datastructure.arguments.NodePredicate;
import com.ethercis.graphql.datastructure.interfaces.Arguments;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.datastructure.itemstructure.ItemTree;
import org.openehr.rm.datastructure.itemstructure.representation.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by christian on 4/12/2017.
 */
public class ItemTreeGetEcisRM extends ItemTreeQLType {

    public ItemTreeGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher items = new DataFetcher() {
        @Override
        public List<Item> get(DataFetchingEnvironment dataFetchingEnvironment) {
            List<Item> items = ((ItemTree) dataFetchingEnvironment.getSource()).getItems();
            Map<String, Object> arguments = dataFetchingEnvironment.getArguments();
            //TODO: do some magic to retrieve the items depending on the predicates
            if (new Arguments(arguments).hasSetArguments()) {
                List<Item> filteredItems = new ArrayList<>();
                for (Item item : items) {

                    if (!new NodePredicate(arguments).match(item))
                        continue;

                    filteredItems.add(item);
                }
                if (filteredItems.size() == 0)
                    items = null;
                else
                    items = filteredItems;
            }
            return items;
        }
    };

    @Override
    public GraphQLObjectType objectType() {
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .useDataFetcher(ITEMS, items)
                .getObjectType();
    }


}
