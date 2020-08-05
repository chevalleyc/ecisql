package com.ethercis.graphql.datatypes.datavalue.ecis_rm_getter;

import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datatypes.datavalue.DvUriQLType;
import graphql.schema.GraphQLObjectType;

/**
 * Created by christian on 4/11/2017.
 */
public class DvUriGetEcisRM extends DvUriQLType {

    public DvUriGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType(){
        GraphQLObjectType objectType = super.objectType();
        return objectType;
    }
}
