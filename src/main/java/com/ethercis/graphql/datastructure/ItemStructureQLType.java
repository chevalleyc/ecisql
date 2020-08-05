package com.ethercis.graphql.datastructure;

import com.ethercis.graphql.commons.EcisQLUnionType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLUnionType;
import graphql.schema.TypeResolver;
import org.openehr.rm.datastructure.itemstructure.ItemList;
import org.openehr.rm.datastructure.itemstructure.ItemSingle;
import org.openehr.rm.datastructure.itemstructure.ItemTree;
import org.openehr.rm.datastructure.itemstructure.representation.Element;

import static graphql.schema.GraphQLUnionType.newUnionType;

/**
 * Created by christian on 3/9/2017.
 */
public class ItemStructureQLType extends EcisQLUnionType {

    private final String id = I_RmObjectQLRegistry.ITEM_STRUCTURE;

    public ItemStructureQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLUnionType unionType() {
        GraphQLUnionType unionType = newUnionType()
                .name(id)
                .description("Abstract parent class of all spatial data types")
                .possibleType(rmObjectType.objectType(I_RmObjectQLRegistry.ITEM_SINGLE))
                .possibleType(rmObjectType.objectType(I_RmObjectQLRegistry.ITEM_LIST))
                .possibleType(rmObjectType.objectType(I_RmObjectQLRegistry.ITEM_TREE))
                .possibleType(rmObjectType.objectType(I_RmObjectQLRegistry.ELEMENT))
                .typeResolver(new TypeResolver() {
                    @Override
                    public GraphQLObjectType getType(Object object) {
                        if (object instanceof ItemSingle)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.ITEM_SINGLE);
                        else if (object instanceof ItemList)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.ITEM_LIST);
                        else if (object instanceof ItemTree)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.ITEM_TREE);
                        else if (object instanceof Element)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.ELEMENT);
                        else
                            throw new IllegalArgumentException("Unhandled type class:" + object.getClass());
                    }
                })
                .build();
        return unionType;
    }

    @Override
    public String id() {
        return id;
    }
}
