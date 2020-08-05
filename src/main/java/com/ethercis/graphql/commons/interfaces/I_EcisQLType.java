package com.ethercis.graphql.commons.interfaces;

import graphql.schema.GraphQLObjectType;

/**
 * Created by christian on 3/7/2017.
 */
public interface I_EcisQLType {
    GraphQLObjectType objectType();
    String id();
}
