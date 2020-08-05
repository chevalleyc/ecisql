package com.ethercis.graphql.datatypes.commons;

import com.ethercis.graphql.commons.interfaces.I_FieldDefinition;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLNonNull;

import static graphql.Scalars.GraphQLString;

/**
 * Created by christian on 3/7/2017.
 */
public class Value implements I_FieldDefinition {

    public GraphQLFieldDefinition fieldDefinition(){
        return new GraphQLFieldDefinition.Builder()
                .name("value")
                .description("Displayable rendition of the item")
                .type(new GraphQLNonNull(GraphQLString))
                .build();
    }
}
