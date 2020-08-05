package com.ethercis.graphql.commons;

import com.ethercis.graphql.commons.interfaces.I_EcisQLInterface;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLInterfaceType;

/**
 * Created by christian on 4/12/2017.
 */
public class EcisQLInterfaceType extends EcisQLBase implements I_EcisQLInterface {

    public EcisQLInterfaceType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLInterfaceType interfaceType() {
        return null;
    }

    @Override
    public String id() {
        return null;
    }
}
