package com.ethercis.graphql.composition;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.QLTypeInterface;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLTypeReference;

import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/9/2017.
 */
public class SectionQLType extends EcisQLType {

    public static final String ITEMS = "items";

    private final String id = I_RmObjectQLRegistry.SECTION;

    protected SectionQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType elementType = newObject()
                .name(id)
                .description("Represents a heading in a heading structure, or section tree")
                .field(
                        newFieldDefinition()
                                .name(ITEMS)
                                .description("Ordered list of content items under this section")
                                .type(new GraphQLList(new GraphQLTypeReference(I_RmObjectQLRegistry.CONTENT_ITEM)))
                                .argument(rmObjectType.argument(I_RmObjectQLRegistry.ARCHETYPE_NODE_ID_ARGUMENT))
                                .argument(rmObjectType.argument(I_RmObjectQLRegistry.NODE_NAME_ARGUMENT))

                )
                .build();
        elementType = new QLTypeInterface(rmObjectType, elementType)
                .withInterface(I_RmObjectQLRegistry.CONTENT_ITEM)
                .getObjectType();

        return elementType;
    }

    @Override
    public String id() {
        return id;
    }
}
