package com.ethercis.graphql.datastructure.interfaces;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datatypes.commons.Value;
import graphql.schema.GraphQLObjectType;

import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/14/2017.
 */
public class UidBaseIdQLType extends EcisQLType {

    private final String id = I_RmObjectQLRegistry.UID_BASE_ID;

    protected UidBaseIdQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType objectType = newObject()
                .name(id)
                .description("Abstract model of UID-based identifiers consisting of a root part and an optional extension")
                .field(
                        new Value().fieldDefinition()
                )
                .build();

//        objectType = rmObjectType.useDataFetcher(objectType, I_RmObjectQLType.UID_BASE_ID);
        return objectType;
    }

    @Override
    public String id() {
        return id;
    }
}
