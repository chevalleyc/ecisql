package com.ethercis.graphql.datatypes.commons;

import com.ethercis.graphql.commons.interfaces.I_FieldDefinition;
import graphql.schema.GraphQLFieldDefinition;

import static graphql.Scalars.GraphQLString;

/**
 * Created by christian on 3/7/2017.
 */
public class FormattingString implements I_FieldDefinition {
    public GraphQLFieldDefinition fieldDefinition() {
        return new GraphQLFieldDefinition.Builder()
                .name("formatting")
                .description("format string")
                .type(GraphQLString)
                .build();
    }
}
