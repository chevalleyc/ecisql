package com.ethercis.graphql.datatypes.datavalue.ecis_rm_getter;

import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datatypes.datavalue.DvProportionQLType;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.datatypes.quantity.DvProportion;

/**
 * Created by christian on 4/11/2017.
 */
public class DvProportionGetEcisRM extends DvProportionQLType {

    public DvProportionGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher numerator = new DataFetcher() {
        @Override
        public Double get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvProportion) dataFetchingEnvironment.getSource()).getNumerator();
        }
    };

    DataFetcher denominator = new DataFetcher() {
        @Override
        public Double get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvProportion) dataFetchingEnvironment.getSource()).getDenominator();
        }
    };

    DataFetcher type = new DataFetcher() {
        @Override
        public Integer get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvProportion) dataFetchingEnvironment.getSource()).getType().getValue();
        }
    };

    DataFetcher precision = new DataFetcher() {
        @Override
        public Integer get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvProportion) dataFetchingEnvironment.getSource()).getPrecision();
        }
    };

    @Override
    public GraphQLObjectType objectType() {
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .useDataFetcher(NUMERATOR, numerator)
                .useDataFetcher(DENOMINATOR, denominator)
                .useDataFetcher(TYPE, type)
                .useDataFetcher(PRECISION, precision)
                .getObjectType();

    }
}
