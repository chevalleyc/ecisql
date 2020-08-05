package com.ethercis.graphql.commons;

import graphql.schema.GraphQLInterfaceType;
import graphql.schema.GraphQLObjectType;

/**
 * Created by christian on 4/14/2017.
 */
public interface I_QLTypeInterface {

    I_QLTypeInterface withInterface(GraphQLInterfaceType interfaceType);

    I_QLTypeInterface withInterface(String interfaceId);

    GraphQLObjectType getObjectType();
}
