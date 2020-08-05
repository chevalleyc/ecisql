package com.ethercis.graphql.commons.interfaces;

import graphql.schema.GraphQLInterfaceType;

/**
 * Created by christian on 3/13/2017.
 */
public interface I_EcisQLItem {
    GraphQLInterfaceType interfaceType();
    String id();
}
