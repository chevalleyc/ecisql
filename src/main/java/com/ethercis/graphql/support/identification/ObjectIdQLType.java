package com.ethercis.graphql.support.identification;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/9/2017.
 */
public class ObjectIdQLType extends EcisQLType {

    public static final String VALUE = "value";
    private final String id = I_RmObjectQLRegistry.HIER_OBJECT_ID;

    protected ObjectIdQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType objectType = newObject()
                .name(id)
                .description("Ancestor class of identifiers of informational objects")
                .field(
                        newFieldDefinition()
                                .name(VALUE)
                                .description("The value of the id in the form defined below.")
                                .type(new GraphQLNonNull(GraphQLString))
                )
                .build();
        return objectType;
    }

    @Override
    public String id() {
        return id;
    }
}
