package com.ethercis.graphql.datatypes.datavalue.ecis_rm_getter;

import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datatypes.datavalue.DvDurationQLType;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;

/**
 * Created by christian on 4/11/2017.
 */
public class DvDurationGetEcisRM extends DvDurationQLType {

    public DvDurationGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher value = new DataFetcher() {
        @Override
        public String get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvDuration) dataFetchingEnvironment.getSource()).getValue();
        }
    };

    @Override
    public GraphQLObjectType objectType() {
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .useDataFetcher(VALUE, value)
                .getObjectType();
    }
}
