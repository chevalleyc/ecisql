package com.ethercis.graphql.commons.interfaces;

import graphql.schema.GraphQLUnionType;

/**
 * Created by christian on 3/13/2017.
 */
public interface I_EcisQLUnionType {
    GraphQLUnionType unionType();
    String id();
}
