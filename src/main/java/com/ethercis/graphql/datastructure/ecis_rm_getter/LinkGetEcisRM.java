package com.ethercis.graphql.datastructure.ecis_rm_getter;

import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;

import com.ethercis.graphql.datastructure.interfaces.LinkQLType;
import graphql.schema.GraphQLObjectType;

/**
 * Created by christian on 4/12/2017.
 */
public class LinkGetEcisRM extends LinkQLType {
    public LinkGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType objectType = super.objectType();
        return objectType;
    }
}
