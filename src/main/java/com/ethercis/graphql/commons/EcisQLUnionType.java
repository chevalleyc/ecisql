package com.ethercis.graphql.commons;

import com.ethercis.graphql.commons.interfaces.I_EcisQLUnionType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLUnionType;

/**
 * Created by christian on 4/12/2017.
 */
public class EcisQLUnionType extends EcisQLBase implements I_EcisQLUnionType {

    public EcisQLUnionType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }


    @Override
    public GraphQLUnionType unionType() {
        return null;
    }

    @Override
    public String id() {
        return null;
    }
}
