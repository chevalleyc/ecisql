package com.ethercis.graphql.datastructure;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.QLTypeInterface;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;

import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/9/2017.
 */
public class ItemListQLType extends EcisQLType {

    public static final String ITEMS = "items";
    private final String id = I_RmObjectQLRegistry.ITEM_LIST;

    protected ItemListQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType elementType = newObject()
                .name(id)
                .description("Logical list data structure, where each item has a valueFetcher and can be referred to by a name and a positional index in the list")
                .field(
                        newFieldDefinition()
                                .name(ITEMS)
                                .description("List of Elements contained by this item single")
                                .argument(rmObjectType.argument(I_RmObjectQLRegistry.ARCHETYPE_NODE_ID_ARGUMENT))
                                .argument(rmObjectType.argument(I_RmObjectQLRegistry.NODE_NAME_ARGUMENT))
                                .type(new GraphQLList(rmObjectType.objectType(I_RmObjectQLRegistry.ELEMENT)))
                )
                .build();
        elementType = new QLTypeInterface(rmObjectType, elementType).withInterface(I_RmObjectQLRegistry.DATA_STRUCTURE).getObjectType();
//        elementType = rmObjectType.useDataFetcher(elementType, I_RmObjectQLType.ITEM_STRUCTURE);
        return elementType;
    }

    @Override
    public String id() {
        return id;
    }
}
