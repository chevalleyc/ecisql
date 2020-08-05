package com.ethercis.graphql.support.identification;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.QLTypeInterface;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLObjectType;

import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/9/2017.
 */
public class UidQLType extends EcisQLType {

    private final String id = I_RmObjectQLRegistry.UID_BASE_ID;

    protected UidQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType objectType = newObject()
                .name(id)
                .build();
        objectType = new QLTypeInterface(rmObjectType, objectType).withInterface(I_RmObjectQLRegistry.UID_BASE_ID).getObjectType();
        return objectType;
    }

    @Override
    public String id() {
        return id;
    }
}
