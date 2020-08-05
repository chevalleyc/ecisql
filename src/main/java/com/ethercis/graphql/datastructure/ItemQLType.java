package com.ethercis.graphql.datastructure;

import com.ethercis.ehr.encode.wrappers.element.ElementWrapper;
import com.ethercis.graphql.commons.EcisQLUnionType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLNullableType;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLUnionType;
import graphql.schema.TypeResolver;
import org.openehr.rm.datastructure.itemstructure.representation.Cluster;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datastructure.itemstructure.representation.Item;

import static graphql.schema.GraphQLUnionType.newUnionType;

/**
 * Created by christian on 3/14/2017.
 */
public class ItemQLType extends EcisQLUnionType {

    private final String id = I_RmObjectQLRegistry.ITEM;

    public ItemQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLUnionType unionType() {
        GraphQLUnionType unionType = newUnionType()
                .name(id)
                .description("The abstract parent of CLUSTER and ELEMENT representation classes")
                .possibleType(rmObjectType.objectType(I_RmObjectQLRegistry.CLUSTER))
                .possibleType(rmObjectType.objectType(I_RmObjectQLRegistry.ELEMENT))
                .typeResolver(new TypeResolver() {
                    @Override
                    public GraphQLObjectType getType(Object object) {
                        return type((Item) object);
                    }
                })
                .build();
        return unionType;
    }

    public GraphQLObjectType type(Item item) {
        if (item instanceof Cluster)
            return rmObjectType.objectType(I_RmObjectQLRegistry.CLUSTER);
        else if (item instanceof Element)
            return rmObjectType.objectType(I_RmObjectQLRegistry.ELEMENT);
        else if (item instanceof ElementWrapper) {
            return rmObjectType.objectType(I_RmObjectQLRegistry.ELEMENT);
        } else
            throw new IllegalArgumentException("Unsupported object class in type resolver:" + item.getClass());
    }

    @Override
    public String id() {
        return id;
    }
}
