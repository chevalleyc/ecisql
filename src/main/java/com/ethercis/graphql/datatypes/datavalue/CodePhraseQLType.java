package com.ethercis.graphql.datatypes.datavalue;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLObjectType;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/7/2017.
 */
public class CodePhraseQLType extends EcisQLType {

    protected static final String TERMINOLOGY_ID = "terminology_id";
    protected static final String CODE_STRING = "code_string";
    protected final String id = I_RmObjectQLRegistry.CODE_PHRASE;

    public CodePhraseQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType graphQLObjectType = newObject()
                .name(id)
                .description("fully coordinated term from a terminology service")
                .field(
                        newFieldDefinition()
                                .name(TERMINOLOGY_ID)
                                .description("Identifier of the distinct terminology")
//                                .dataFetcher(terminologyID)
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.TERMINOLOGY_ID))
                )
                .field(
                        newFieldDefinition()
                                .name(CODE_STRING)
                                .description("key used by the terminology service for identification")
//                                .dataFetcher(codeString)
                                .type(GraphQLString)
                )
                .build();
        return graphQLObjectType;
    }

    @Override
    public String id() {
        return id;
    }
}
