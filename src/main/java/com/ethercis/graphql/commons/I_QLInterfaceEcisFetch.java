package com.ethercis.graphql.commons;

import graphql.schema.DataFetcher;
import graphql.schema.GraphQLInterfaceType;
import graphql.schema.GraphQLObjectType;

/**
 * Created by christian on 4/16/2017.
 */
public interface I_QLInterfaceEcisFetch {

    I_QLInterfaceEcisFetch useDataFetcher(String fieldId, DataFetcher fetcher);

    GraphQLInterfaceType getInterfaceType();
}
