package com.ethercis.graphql.datatypes.interfaces;

import com.ethercis.graphql.commons.EcisQLInterfaceType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLInterfaceType;

/**
 * Created by christian on 3/7/2017.
 */
public class DataValueQLIF extends EcisQLInterfaceType {

    private final String id = I_RmObjectQLRegistry.DATA_VALUE;

    protected DataValueQLIF(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    //TODO: support interval!
    @Override
    public GraphQLInterfaceType interfaceType() {
//        GraphQLInterfaceType  graphQLInterfaceType = newInterface()
//                .name(id)
//                .description("Abstract class defining the concept of true quantified values")
//                .typeResolver(new TypeResolver() {
//                    @Override
//                    public GraphQLObjectType getType(Object o) {
//                        return new DataValueQLType((DataValue)o).objectType();
//                    }
//                })
//                .build();
//        return graphQLInterfaceType;
        return null;
    }

    @Override
    public String id() {
        return id;
    }
}
