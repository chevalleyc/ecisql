package com.ethercis.graphql.datastructure.interfaces.ecis_rm_getter;

import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datastructure.interfaces.LinkQLIF;
import graphql.schema.GraphQLInterfaceType;

/**
 * Created by christian on 4/12/2017.
 */
public class LinkIFGetEcisRM extends LinkQLIF {

    public LinkIFGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLInterfaceType interfaceType() {
        GraphQLInterfaceType interfaceType = super.interfaceType();
        return interfaceType;
    }
}
