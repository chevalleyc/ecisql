package com.ethercis.graphql.datatypes.datavalue.ecis_rm_getter;

import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datatypes.interfaces.DvDateTimeStaticsQLType;
import graphql.schema.GraphQLObjectType;

/**
 * Created by christian on 4/12/2017.
 */
public class DvDateTimeStaticsGetEcisRM extends DvDateTimeStaticsQLType {

    public DvDateTimeStaticsGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType objectType = super.objectType();
//        objectType = rmObjectType.registerDataFetcher(objectType, VALUE, value);
        return objectType;
    }
}
