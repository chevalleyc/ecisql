package com.ethercis.graphql.commons;

import com.ethercis.graphql.commons.interfaces.I_EcisQLType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLObjectType;

/**
 * Created by christian on 4/12/2017.
 */
public class EcisQLType extends EcisQLBase implements I_EcisQLType {

    public EcisQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        return null;
    }

    @Override
    public String id() {
        return null;
    }
}
