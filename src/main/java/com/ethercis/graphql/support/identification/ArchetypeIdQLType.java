package com.ethercis.graphql.support.identification;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.*;

import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

import static graphql.Scalars.GraphQLString;

/**
 * Created by christian on 3/9/2017.
 */
public class ArchetypeIdQLType extends EcisQLType {

    public static final String VALUE = "value";
    private final String id = I_RmObjectQLRegistry.ARCHETYPE_ID;


    public ArchetypeIdQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType elementType = newObject()
                .name(id)
                .description("Identifier for archetypes. Ideally these would identify globally unique archetypes")
                .field(
                        newFieldDefinition()
                                .name(VALUE)
                                .description("The value of the id in the form defined below.")
                                .type(new GraphQLNonNull(GraphQLString))
                )
                .build();
        return elementType;
    }

    @Override
    public String id() {
        return id;
    }
}
