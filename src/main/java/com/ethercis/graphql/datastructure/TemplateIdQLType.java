package com.ethercis.graphql.datastructure;

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
public class TemplateIdQLType extends EcisQLType {

    public static final String VALUE = "value";
    private final String id = I_RmObjectQLRegistry.TEMPLATE_ID;

    protected TemplateIdQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }


    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType elementType = newObject()
                .name(id)
                .description("Archetypes act as the configuration basis for the particular structures of instances defined by the reference model")
                .field(
                        newFieldDefinition()
                                .name(VALUE)
                                .description("The value of the id in the form defined below.")
//                                .dataFetcher(value)
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
