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
public class DvTextQLType extends EcisQLType {

    public static final String VALUE = "value";
    public static final String MAPPINGS = "mappings";
    public static final String HYPERLINK = "hyperlink";
    public static final String LANGUAGE = "language";
    public static final String ENCODING = "encoding";
    public static final String FORMATTING = "formatting";
    private final String id = I_RmObjectQLRegistry.DV_TEXT;

    public DvTextQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType graphQLObjectType = newObject()
                .name(id)
                .description("text item")
                .field(newFieldDefinition()
                                .name(VALUE)
                                .description("Displayable rendition of the item")
                                .type(new GraphQLNonNull(GraphQLString))
                                .build()
                )
                .field(
                        newFieldDefinition()
                                .name(MAPPINGS)
                                .description("Terms from other terminologies")
//                                .dataFetcher(termMapping)
                                .type(new GraphQLList(rmObjectType.objectType(I_RmObjectQLRegistry.TERM_MAPPING)))
                                .build()
                )
                .field(
                        newFieldDefinition()
                                .name(HYPERLINK)
                                .description("Optional link sitting behind a section of plain text or coded term item")
//                                .dataFetcher(hyperlink)
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.DV_URI))
                                .build()
                )
                .field(
                        newFieldDefinition()
                                .name(LANGUAGE)
                                .description("Optional indicator of the localised language")
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.CODE_PHRASE))
//                                .dataFetcher(language)
                                .build()
                )
                .field(
                        newFieldDefinition()
                                .name(ENCODING)
                                .description("Name of character encoding scheme in which this value is encoded")
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.CODE_PHRASE))
//                                .dataFetcher(encoding)
                                .build()
                )
                .field(
                        newFieldDefinition()
                                .name(FORMATTING)
                                .description("format string")
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
