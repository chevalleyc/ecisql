package com.ethercis.graphql.commons;

import graphql.schema.DataFetcher;
import graphql.schema.GraphQLObjectType;

/**
 * Created by christian on 4/16/2017.
 */
public interface I_QLEcisFetch {

    I_QLEcisFetch useDataFetcher(String fieldId, DataFetcher fetcher);
}
