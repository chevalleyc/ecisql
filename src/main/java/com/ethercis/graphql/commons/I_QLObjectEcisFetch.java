package com.ethercis.graphql.commons;

import graphql.schema.DataFetcher;
import graphql.schema.GraphQLObjectType;

/**
 * Created by christian on 4/16/2017.
 */
public interface I_QLObjectEcisFetch {

    I_QLObjectEcisFetch useDataFetcher(String fieldId, DataFetcher fetcher);

    GraphQLObjectType getObjectType();
}
