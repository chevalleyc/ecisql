package com.ethercis.graphql.datastructure.arguments;

import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLList;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLArgument.newArgument;

/**
 * Created by christian on 4/10/2017.
 */
public class NodeNameArgument {

    private final String id = I_RmObjectQLRegistry.NODE_NAME_ARGUMENT;
    private static final String field = "name_value";

    private I_RmObjectQLRegistry rmObjectType;

    public NodeNameArgument(I_RmObjectQLRegistry rmObjectQLType) {
        this.rmObjectType = rmObjectQLType;
    }

    public GraphQLArgument argument() {
        GraphQLArgument argument = newArgument()
                .name(field)
                .description("node name predicate")
                .type(new GraphQLList(GraphQLString))
                .build();

        return argument;
    }

    public static String getField() {
        return field;
    }
}
