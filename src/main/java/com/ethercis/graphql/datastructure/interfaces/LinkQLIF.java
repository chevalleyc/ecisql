package com.ethercis.graphql.datastructure.interfaces;

import com.ethercis.graphql.commons.EcisQLInterfaceType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.*;

import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLInterfaceType.newInterface;

/**
 * Created by christian on 3/7/2017.
 */
public class LinkQLIF extends EcisQLInterfaceType {

    public static final String MEANING = "meaning";
    public static final String TYPE = "type";
    public static final String TARGET = "target";
    private final String id = I_RmObjectQLRegistry.LINK;

    protected LinkQLIF(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    //TODO: support interval!
    @Override
    public GraphQLInterfaceType interfaceType() {
        GraphQLInterfaceType  graphQLInterfaceType = newInterface()
                .name(id)
                .description("The LINK type defines a logical relationship between two items, such as two ENTRYs or an ENTRY and a COMPOSITION")
                .field(
                        newFieldDefinition()
                                .name(MEANING)
                                .description("Used to describe the relationship")
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.CODE_PHRASE)))
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
                .typeResolver(new TypeResolver() {
                    @Override
                    public GraphQLObjectType getType(Object o) {
                        return rmObjectType.objectType(I_RmObjectQLRegistry.LINK);
                    }
                })
                .build();
        return graphQLInterfaceType;
    }

    @Override
    public String id() {
        return id;
    }
}
