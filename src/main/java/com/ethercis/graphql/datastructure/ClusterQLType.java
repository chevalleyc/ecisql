package com.ethercis.graphql.datastructure;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.QLTypeInterface;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.*;

import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/9/2017.
 */
public class ClusterQLType extends EcisQLType {

    public static final String ITEMS = "items";
    private final String id = I_RmObjectQLRegistry.CLUSTER;


    protected ClusterQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType elementType = newObject()
                .name(id)
                .description("The grouping variant of ITEM")
                .field(
                        newFieldDefinition()
                                .name(ITEMS)
                                .description("Ordered list of items")
//                                .dataFetcher(itemsFetcher)
                                .type(new GraphQLList(new GraphQLTypeReference(I_RmObjectQLRegistry.ITEM)))
                                .argument(rmObjectType.argument(I_RmObjectQLRegistry.ARCHETYPE_NODE_ID_ARGUMENT))
                                .argument(rmObjectType.argument(I_RmObjectQLRegistry.NODE_NAME_ARGUMENT))
                                .build()
                )
                .build();
        elementType = new QLTypeInterface(rmObjectType, elementType).withInterface(I_RmObjectQLRegistry.ITEM).getObjectType();
        return elementType;
    }

    @Override
    public String id() {
        return id;
    }
}
