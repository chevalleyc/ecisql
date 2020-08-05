package com.ethercis.graphql.datastructure.interfaces;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLObjectType;

import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/14/2017.
 */
public class _ItemStructureQLType extends EcisQLType {

    private final String id = I_RmObjectQLRegistry.ITEM_STRUCTURE;


    public _ItemStructureQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType objectType = newObject()
                .name(id)
                .field(
                        newFieldDefinition()
                                .name("archetype_details")
                                .description("Details of archetyping used on this node")
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.ARCHETYPED))
                )
                .field(
                        newFieldDefinition()
                                .name("uid")
                                .description("Optional globally unique object identifier for root points of archetyped structures")
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.UID))
                )
                .field(
                        newFieldDefinition()
                                .name("link")
                                .description("Links to other archetyped structures")
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.LINK))
                )
                .withInterface(rmObjectType.interfaceType(I_RmObjectQLRegistry.ITEM_STRUCTURE))
                .build();
        return objectType;
    }

    @Override
    public String id() {
        return id;
    }
}
