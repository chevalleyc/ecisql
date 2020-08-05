package com.ethercis.graphql.datastructure.arguments;

import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLList;

import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.Scalars.GraphQLString;

/**
 * Created by christian on 4/10/2017.
 */
public class ArchetypeNodeIdArgument {

    private final String id = I_RmObjectQLRegistry.ARCHETYPE_NODE_ID_ARGUMENT;
    private static final String field = "archetype_node_id";

    private I_RmObjectQLRegistry rmObjectType;

    public ArchetypeNodeIdArgument(I_RmObjectQLRegistry rmObjectQLType) {
        this.rmObjectType = rmObjectQLType;
    }

    public GraphQLArgument argument() {
        GraphQLArgument argument = newArgument()
                .name(field)
                .description("node id predicate")
                .type(new GraphQLList(GraphQLString))
                .build();

        return argument;
    }

    public static String getField(){return field;}

}
