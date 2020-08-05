package com.ethercis.graphql.commons;

import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.generator.QueryMode;

/**
 * Created by christian on 4/12/2017.
 */
abstract class EcisQLBase {

    private QueryMode queryStrategy = QueryMode.SET_VALUES;

    protected I_RmObjectQLRegistry rmObjectType;

    protected EcisQLBase(I_RmObjectQLRegistry rmObjectQLType) {
        this.rmObjectType = rmObjectQLType;
    }

    protected boolean isSetValuesOnly(){
        return queryStrategy.equals(QueryMode.SET_VALUES);
    }
}
