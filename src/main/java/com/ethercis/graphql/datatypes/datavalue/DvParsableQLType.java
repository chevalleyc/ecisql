package com.ethercis.graphql.datatypes.datavalue;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/7/2017.
 */
public class DvParsableQLType extends EcisQLType {

    public static final String VALUE = "value";
    public static final String LANGUAGE = "language";
    public static final String CHARSET = "charset";
    public static final String FORMALISM = "formalism";

    private final String id = I_RmObjectQLRegistry.DV_PARSABLE;

    protected DvParsableQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType graphQLObjectType = newObject()
                .name(id)
                .description("text item whose value must be the rubric from a controlled terminology")
                .field(
                        newFieldDefinition()
                                .name(CHARSET)
                                .description("Name of character encoding scheme in which this value is encoded")
//                                .dataFetcher(value)
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.CODE_PHRASE))
                                .build()
                )
                .field(
                        newFieldDefinition()
                                .name(LANGUAGE)
                                .description("Optional indicator of the localised language in which the data is written")
//                                .dataFetcher(value)
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.CODE_PHRASE))
                                .build()
                )
                .field(
                        newFieldDefinition()
                                .name(VALUE)
                                .description("The string, which may validly be empty in some syntaxes")
//                                .dataFetcher(termMapping)
                                .type(new GraphQLNonNull(GraphQLString))
                                .build()
                )
                .field(
                        newFieldDefinition()
                                .name(FORMALISM)
                                .description("Name of the formalism")
//                                .dataFetcher(termMapping)
                                .type(new GraphQLNonNull(GraphQLString))
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
