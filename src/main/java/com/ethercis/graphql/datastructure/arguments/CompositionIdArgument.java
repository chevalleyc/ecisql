package com.ethercis.graphql.datastructure.arguments;

import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLList;

import static graphql.Scalars.*;
import static graphql.schema.GraphQLArgument.newArgument;

/**
 * Created by christian on 4/10/2017.
 */
public class CompositionIdArgument {

    private final String id = I_RmObjectQLRegistry.COMPOSITION_ID_ARGUMENT;
    private static final String field = "id";

    private I_RmObjectQLRegistry rmObjectType;

    public CompositionIdArgument(I_RmObjectQLRegistry rmObjectQLType) {
        this.rmObjectType = rmObjectQLType;
    }

    public GraphQLArgument argument() {
        GraphQLArgument argument = newArgument()
                .name(field)
                .description("composition id predicate")
                .type(new GraphQLList(GraphQLString))
                .build();

        return argument;
    }

    public static String getField(){return field;}

    public String getId() {
        return id;
    }
}
