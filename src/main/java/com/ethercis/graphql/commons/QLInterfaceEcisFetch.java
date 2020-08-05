package com.ethercis.graphql.commons;

import com.ethercis.graphql.commons.interfaces.I_EcisQLInterface;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLInterfaceType;
import graphql.schema.GraphQLObjectType;

/**
 * Created by christian on 4/14/2017.
 */
public class QLInterfaceEcisFetch extends QLEcisFetch implements I_QLInterfaceEcisFetch {


    public QLInterfaceEcisFetch(I_RmObjectQLRegistry registry, GraphQLInterfaceType objectType) {
        super(registry, objectType);
    }

    @Override
    public I_QLInterfaceEcisFetch useDataFetcher(String fieldId, DataFetcher fetcher) {
        holder = registry.registerDataFetcher((GraphQLInterfaceType)holder, fieldId, fetcher);
        return this;
    }

    @Override
    public GraphQLInterfaceType getInterfaceType(){
        return (GraphQLInterfaceType)holder;
    }

}
