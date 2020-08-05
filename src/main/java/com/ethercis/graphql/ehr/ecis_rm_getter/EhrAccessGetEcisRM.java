package com.ethercis.graphql.ehr.ecis_rm_getter;

import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.ehr.EhrAccessQLType;
import graphql.schema.GraphQLObjectType;

/**
 * Created by christian on 4/12/2017.
 */
public class EhrAccessGetEcisRM extends EhrAccessQLType {

    public EhrAccessGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }


    @Override
    public GraphQLObjectType objectType() {
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .getObjectType();
    }

}
