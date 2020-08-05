package com.ethercis.graphql.datastructure.ecis_rm_getter;

import com.ethercis.ehr.encode.wrappers.element.ElementWrapper;
import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datastructure.ClusterQLType;
import com.ethercis.graphql.datastructure.arguments.NodePredicate;
import com.ethercis.graphql.datastructure.interfaces.Arguments;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.datastructure.itemstructure.representation.Cluster;
import org.openehr.rm.datastructure.itemstructure.representation.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by christian on 4/12/2017.
 */
public class ClusterGetEcisRM extends ClusterQLType {

    public ClusterGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }


    private  boolean isEmptyCluster(List<Item> items){
        //if this cluster references elements, are they all empty?
        boolean isEmpty = true;
        for (Item item: items){
            if (item instanceof ElementWrapper){
                if (((ElementWrapper)item).dirtyBitSet()) {
                    isEmpty = false;
                    break;
                }
            }
            else if (item instanceof Cluster){
                isEmpty = false;
                break;
            }
        }

        return isEmpty;
    }

    DataFetcher itemsFetcher = new DataFetcher() {
        @Override
        public List<Item> get(DataFetchingEnvironment dataFetchingEnvironment) {
            List<Item> items = ((Cluster)dataFetchingEnvironment.getSource()).getItems();
            if (isEmptyCluster(items))
                return  null;

            Map<String, Object> arguments = dataFetchingEnvironment.getArguments();
            //TODO: do some magic to retrieve the items depending on the predicates
            if (new Arguments(arguments).hasSetArguments()){
                List<Item> filteredItems = new ArrayList<>();
                for (Item item: items){
                    if (!new NodePredicate(arguments).match(item))
                        continue;
                    if (item instanceof ElementWrapper && isSetValuesOnly()){
                        if (!(((ElementWrapper)item).dirtyBitSet()))
                            continue;
                    }
                    filteredItems.add(item);
                }
                if (filteredItems.size() == 0)
                    items =  null;
                else
                    items = filteredItems;
            }
            return items;
        }
    };

    @Override
    public GraphQLObjectType objectType(){
        return new QLObjectEcisFetch(rmObjectType, super.objectType()).useDataFetcher(ITEMS, itemsFetcher).getObjectType();
    }

}
