package com.ethercis.graphql.commons;

import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLObjectType;

/**
 * Created by christian on 4/14/2017.
 */
public class QLObjectEcisFetch extends QLEcisFetch implements I_QLObjectEcisFetch {


    public QLObjectEcisFetch(I_RmObjectQLRegistry registry, GraphQLObjectType objectType) {
        super(registry, objectType);
    }

    @Override
    public I_QLObjectEcisFetch useDataFetcher(String fieldId, DataFetcher fetcher) {
        holder = registry.registerDataFetcher((GraphQLObjectType)holder, fieldId, fetcher);
        return this;
    }

    @Override
    public GraphQLObjectType getObjectType(){
        return (GraphQLObjectType)holder;
    }

}
