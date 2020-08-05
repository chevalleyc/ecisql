package com.ethercis.graphql.datatypes.interfaces.ecis_rm_getter;

import com.ethercis.graphql.commons.RmObjectQLRegistry;
import com.ethercis.graphql.datatypes.interfaces.DvDateTimeStaticsQLIF;
import graphql.schema.GraphQLInterfaceType;

/**
 * Created by christian on 4/11/2017.
 */
public class DvDateTimeStaticsIFGetEcisRM extends DvDateTimeStaticsQLIF {

    public DvDateTimeStaticsIFGetEcisRM(RmObjectQLRegistry rmObjectQLRegistry) {
        super(rmObjectQLRegistry);
    }

    @Override
    public GraphQLInterfaceType interfaceType(){
        GraphQLInterfaceType interfaceType = super.interfaceType();
        return interfaceType;
    }
}
