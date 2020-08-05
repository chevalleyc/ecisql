package com.ethercis.graphql.datatypes.datavalue.ecis_rm_getter;

import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datatypes.datavalue.DvIntervalQLType;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.datatypes.quantity.DvInterval;
import org.openehr.rm.datatypes.quantity.DvOrdered;

/**
 * Created by christian on 4/12/2017.
 */
public class DvIntervalGetEcisRM extends DvIntervalQLType {

    DataFetcher lower = new DataFetcher() {
        @Override
        public DvOrdered get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvInterval) dataFetchingEnvironment.getSource()).getLower();
        }
    };

    DataFetcher upper = new DataFetcher() {
        @Override
        public DvOrdered get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvInterval) dataFetchingEnvironment.getSource()).getUpper();
        }
    };

    DataFetcher lowerUnbounded = new DataFetcher() {
        @Override
        public Boolean get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvInterval) dataFetchingEnvironment.getSource()).isLowerUnbounded();
        }
    };

    DataFetcher upperUnbounded = new DataFetcher() {
        @Override
        public Boolean get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvInterval) dataFetchingEnvironment.getSource()).isUpperUnbounded();
        }
    };

    DataFetcher lowerIncluded = new DataFetcher() {
        @Override
        public Boolean get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvInterval) dataFetchingEnvironment.getSource()).isLowerIncluded();
        }
    };

    DataFetcher upperIncluded = new DataFetcher() {
        @Override
        public Boolean get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvInterval) dataFetchingEnvironment.getSource()).isUpperIncluded();
        }
    };

    public DvIntervalGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .useDataFetcher(LOWER, lower)
                .useDataFetcher(UPPER, upper)
                .useDataFetcher(LOWER_UNBOUNDED, lowerUnbounded)
                .useDataFetcher(UPPER_UNBOUNDED, upperUnbounded)
                .useDataFetcher(LOWER_INCLUDED, lowerIncluded)
                .useDataFetcher(UPPER_INCLUDED, upperIncluded)
                .getObjectType();
    }
}
