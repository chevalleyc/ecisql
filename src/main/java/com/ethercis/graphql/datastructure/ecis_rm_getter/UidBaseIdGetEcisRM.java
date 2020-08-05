package com.ethercis.graphql.datastructure.ecis_rm_getter;

import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datastructure.interfaces.UidBaseIdQLType;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.datastructure.itemstructure.representation.Cluster;
import org.openehr.rm.datastructure.itemstructure.representation.Item;

import java.util.List;

/**
 * Created by christian on 4/12/2017.
 */
public class UidBaseIdGetEcisRM extends UidBaseIdQLType {

    public UidBaseIdGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }


    DataFetcher itemsFetcher = new DataFetcher() {
        @Override
        public List<Item> get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((Cluster)dataFetchingEnvironment.getSource()).getItems();
        }
    };

    @Override
    public GraphQLObjectType objectType(){
        GraphQLObjectType objectType = super.objectType();
//        objectType = rmObjectType.registerDataFetcher(objectType, ITEMS, itemsFetcher);
        return objectType;
    }

}
