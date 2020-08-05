package com.ethercis.graphql.datastructure.ecis_rm_getter;

import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datastructure.ItemListQLType;
import graphql.schema.GraphQLObjectType;

/**
 * Created by christian on 4/12/2017.
 */
public class ItemListGetEcisRM extends ItemListQLType {

    public ItemListGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType objectType = super.objectType();
//        objectType = rmObjectType.registerDataFetcher(objectType, VALUE, value);
        return objectType;
    }

}
