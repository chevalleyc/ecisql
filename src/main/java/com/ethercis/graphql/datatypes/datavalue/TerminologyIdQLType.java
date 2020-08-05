package com.ethercis.graphql.datatypes.datavalue;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLObjectType;

import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

import static graphql.Scalars.GraphQLString;

/**
 * Created by christian on 3/7/2017.
 */
public class TerminologyIdQLType extends EcisQLType {

    public static final String VALUE = "value";
    private final String id = I_RmObjectQLRegistry.TERMINOLOGY_ID;

    public TerminologyIdQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType graphQLObjectType = newObject()
                .name(id)
                .description("Identifier for terminologies")
                .field(
                        newFieldDefinition()
                                .name(VALUE)
                                .description("Boolean value of this item")
//                                .dataFetcher(value)
                                .type(GraphQLString)
                                .build()
                )
                .build();
        return graphQLObjectType;
    }

    @Override
    public String id() {
        return id;
    }
}
