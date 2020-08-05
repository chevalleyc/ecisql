package com.ethercis.graphql.support.identification.ecis_rm_getter;

import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.support.identification.PartyRefQLType;
import graphql.schema.GraphQLObjectType;

/**
 * Created by christian on 4/12/2017.
 */
public class PartyRefGetEcisRM extends PartyRefQLType {

    public PartyRefGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType objectType = super.objectType();
//        objectType = rmObjectType.registerDataFetcher(objectType, VALUE, value);
        return objectType;
    }
}
