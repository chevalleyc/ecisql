package com.ethercis.graphql.datastructure.interfaces.ecis_rm_getter;

import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datastructure.interfaces.UidBaseIdQLIF;
import graphql.schema.GraphQLInterfaceType;

/**
 * Created by christian on 4/12/2017.
 */
public class UidBaseIdIFGetEcisRM extends UidBaseIdQLIF {

    public UidBaseIdIFGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLInterfaceType interfaceType() {
        GraphQLInterfaceType interfaceType = super.interfaceType();
        return interfaceType;
    }
}
