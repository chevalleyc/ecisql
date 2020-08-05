package com.ethercis.graphql.datatypes.datavalue;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLTypeReference;

import static graphql.Scalars.GraphQLChar;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/7/2017.
 */
public class TermMappingQLType extends EcisQLType {

    private final String id = I_RmObjectQLRegistry.TERM_MAPPING;

    public TermMappingQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType graphQLObjectType = newObject()
                .name(id)
                .description("coded term mapped to a DV_TEXT")
                .field(
                        newFieldDefinition()
                                .name("match")
                                .description("relative match of the target")
                                .type(GraphQLChar)
                                .build()

                )
                .field(
                        newFieldDefinition()
                                .name("purpose")
                                .description("Purpose of the mapping")
                                .type(new GraphQLTypeReference(I_RmObjectQLRegistry.DV_CODED_TEXT))
                                .build()

                )
                .field(
                        newFieldDefinition()
                                .name("target")
                                .description("target term of the mapping")
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.CODE_PHRASE))
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
