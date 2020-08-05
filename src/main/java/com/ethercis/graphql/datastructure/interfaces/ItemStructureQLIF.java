package com.ethercis.graphql.datastructure.interfaces;

import com.ethercis.graphql.commons.EcisQLInterfaceType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLInterfaceType;
import graphql.schema.GraphQLObjectType;
import graphql.schema.TypeResolver;

import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLInterfaceType.newInterface;

/**
 * Created by christian on 3/9/2017.
 */
public class ItemStructureQLIF extends EcisQLInterfaceType {

    public static final String ARCHETYPE_DETAILS = "archetype_details";
    public static final String UID = "uid";
    public static final String LINKS = "links";
    private final String id = I_RmObjectQLRegistry.ITEM_STRUCTURE;

    protected ItemStructureQLIF(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLInterfaceType interfaceType() {
        GraphQLInterfaceType graphQLInterfaceType = newInterface()
                .name(id)
                .field(
                        newFieldDefinition()
                                .name(ARCHETYPE_DETAILS)
                                .description("Details of archetyping used on this node")
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.ARCHETYPED))
                )
                .field(
                        newFieldDefinition()
                                .name(UID)
                                .description("Optional globally unique object identifier for root points of archetyped structures")
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.UID))
                )
                .field(
                        newFieldDefinition()
                                .name(LINKS)
                                .description("Links to other archetyped structures")
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.LINK))
                )
                .typeResolver(new TypeResolver() {
                    @Override
                    public GraphQLObjectType getType(Object o) {
                        return rmObjectType.objectType(I_RmObjectQLRegistry.ITEM_STRUCTURE);
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
