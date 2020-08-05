package com.ethercis.graphql.datastructure.interfaces;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;

import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/14/2017.
 */
public class LinkQLType extends EcisQLType {

    public static final String MEANING = "meaning";
    public static final String TYPE = "type";
    public static final String TARGET = "target";

    private final String id = I_RmObjectQLRegistry.LINK;

    protected LinkQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType objectType = newObject()
                .name(id)
                .description("The LINK type defines a logical relationship between two items, such as two ENTRYs or an ENTRY and a COMPOSITION")
                .field(
                        newFieldDefinition()
                                .name(MEANING)
                                .description("Used to describe the relationship")
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.DV_TEXT)))
                )
                .field(
                        newFieldDefinition()
                                .name(TYPE)
                                .description("The type attribute is used to indicate a clinical")
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.DV_TEXT)))
                )
                .field(
                        newFieldDefinition()
                                .name(TARGET)
                                .description("The logical to object in the link relation")
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.DV_TEXT)))
                )
                .withInterface(new LinkQLIF(rmObjectType).interfaceType())
                .build();
        return objectType;
    }

    @Override
    public String id() {
        return id;
    }
}
