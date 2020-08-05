package com.ethercis.graphql.commons;

import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLType;

/**
 * Created by christian on 4/14/2017.
 */
abstract class QLEcisFetch  {

    protected I_RmObjectQLRegistry registry;
    protected GraphQLType holder;

    public QLEcisFetch(I_RmObjectQLRegistry registry, GraphQLType holder) {
        this.registry = registry;
        this.holder = holder;
    }
}
