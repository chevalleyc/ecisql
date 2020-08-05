package com.ethercis.graphql.datatypes.datavalue.ecis_rm_getter;

import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datatypes.datavalue.DvEhrUriQLType;
import graphql.schema.GraphQLObjectType;

/**
 * Created by christian on 4/11/2017.
 */
public class DvEhrUriGetEcisRM extends DvEhrUriQLType {

    public DvEhrUriGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType(){
        GraphQLObjectType objectType = super.objectType();
//        rmObjectType.registerDataFetcher(objectType, VALUE, value);
        return objectType;
    }
}
