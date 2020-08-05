package com.ethercis.graphql.datatypes.datavalue.ecis_rm_getter;

import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datatypes.datavalue.ReferenceRangeQLType;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.datatypes.quantity.DvInterval;
import org.openehr.rm.datatypes.quantity.ReferenceRange;
import org.openehr.rm.datatypes.text.DvText;

/**
 * Created by christian on 4/11/2017.
 */
public class ReferenceRangeGetEcisRM extends ReferenceRangeQLType {

    public ReferenceRangeGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher meaning = new DataFetcher() {
        @Override
        public DvText get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((ReferenceRange) dataFetchingEnvironment.getSource()).getMeaning();
        }
    };

    DataFetcher range = new DataFetcher() {
        @Override
        public DvInterval get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((ReferenceRange) dataFetchingEnvironment.getSource()).getRange();
        }
    };

    @Override
    public GraphQLObjectType objectType() {
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .useDataFetcher(MEANING, meaning)
                .useDataFetcher(RANGE, range)
                .getObjectType();
    }
}
