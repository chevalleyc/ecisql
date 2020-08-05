package com.ethercis.graphql.support.identification.ecis_rm_getter;

import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.support.identification.HierObjectIdQLType;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.support.identification.HierObjectID;

/**
 * Created by christian on 4/12/2017.
 */
public class HierObjectIdGetEcisRM extends HierObjectIdQLType {

    public HierObjectIdGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher value = new DataFetcher<String>() {
        @Override
        public String get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((HierObjectID) dataFetchingEnvironment.getSource()).getValue();
        }
    };

    @Override
    public GraphQLObjectType objectType() {
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .useDataFetcher(VALUE, value)
                .getObjectType();
    }
}
