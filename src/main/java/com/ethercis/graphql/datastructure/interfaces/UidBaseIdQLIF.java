package com.ethercis.graphql.datastructure.interfaces;

import com.ethercis.graphql.commons.EcisQLInterfaceType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datatypes.commons.Value;
import graphql.schema.GraphQLInterfaceType;
import graphql.schema.GraphQLObjectType;
import graphql.schema.TypeResolver;

import static graphql.schema.GraphQLInterfaceType.newInterface;

/**
 * Created by christian on 3/7/2017.
 */
public class UidBaseIdQLIF extends EcisQLInterfaceType {

    private final String id = I_RmObjectQLRegistry.UID_BASE_ID;

    protected UidBaseIdQLIF(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    //TODO: support interval!
    @Override
    public GraphQLInterfaceType interfaceType() {
        GraphQLInterfaceType  graphQLInterfaceType = newInterface()
                .name(id)
                .description("Abstract model of UID-based identifiers consisting of a root part and an optional extension")
                .field(
                        new Value().fieldDefinition()
                )
                .typeResolver(new TypeResolver() {
                    @Override
                    public GraphQLObjectType getType(Object o) {
                        return rmObjectType.objectType(I_RmObjectQLRegistry.UID_BASE_ID);
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
