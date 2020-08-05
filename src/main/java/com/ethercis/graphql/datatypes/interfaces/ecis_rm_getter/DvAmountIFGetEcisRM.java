package com.ethercis.graphql.datatypes.interfaces.ecis_rm_getter;

import com.ethercis.graphql.commons.QLInterfaceEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datatypes.interfaces.DvAmountQLIF;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLInterfaceType;
import org.openehr.rm.datatypes.quantity.DvAmount;

/**
 * Created by christian on 4/11/2017.
 */
public class DvAmountIFGetEcisRM extends DvAmountQLIF {

    public DvAmountIFGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher accuracyPercent = new DataFetcher() {
        @Override
        public Boolean get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvAmount) dataFetchingEnvironment.getSource()).isAccuracyPercent();
        }
    };

    DataFetcher accuracy = new DataFetcher() {
        @Override
        public Double get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvAmount) dataFetchingEnvironment.getSource()).getAccuracy();
        }
    };

    @Override
    public GraphQLInterfaceType interfaceType() {
        return new QLInterfaceEcisFetch(rmObjectType, super.interfaceType())
                .useDataFetcher(ACCURACY_PERCENT, accuracyPercent)
                .useDataFetcher(ACCURACY, accuracy)
                .getInterfaceType();
    }
}
