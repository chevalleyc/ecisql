package com.ethercis.graphql.datatypes.datavalue.ecis_rm_getter;

import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datatypes.datavalue.DvQuantityQLType;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.datatypes.quantity.DvQuantity;

/**
 * Created by christian on 4/11/2017.
 */
public class DvQuantityGetEcisRM extends DvQuantityQLType {

    public DvQuantityGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher magnitudeFetcher = new DataFetcher() {
        @Override
        public Double get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvQuantity) dataFetchingEnvironment.getSource()).getMagnitude();
        }
    };

    DataFetcher unitFetcher = new DataFetcher() {
        @Override
        public String get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvQuantity) dataFetchingEnvironment.getSource()).getUnits();
        }
    };


    DataFetcher precisionFetcher = new DataFetcher() {
        @Override
        public Integer get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvQuantity) dataFetchingEnvironment.getSource()).getPrecision();
        }
    };

    @Override
    public GraphQLObjectType objectType() {
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .useDataFetcher(MAGNITUDE, magnitudeFetcher)
                .useDataFetcher(UNITS, unitFetcher)
                .useDataFetcher(PRECISION, precisionFetcher)
                .getObjectType();
    }
}
