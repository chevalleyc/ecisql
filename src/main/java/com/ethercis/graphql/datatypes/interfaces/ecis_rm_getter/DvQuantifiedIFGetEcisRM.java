package com.ethercis.graphql.datatypes.interfaces.ecis_rm_getter;

import com.ethercis.graphql.commons.QLInterfaceEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datatypes.interfaces.DvQuantifiedQLIF;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLInterfaceType;
import org.openehr.rm.datatypes.quantity.DvInterval;
import org.openehr.rm.datatypes.quantity.DvQuantity;
import org.openehr.rm.datatypes.quantity.ReferenceRange;
import org.openehr.rm.datatypes.text.CodePhrase;

import java.util.List;

/**
 * Created by christian on 4/11/2017.
 */
public class DvQuantifiedIFGetEcisRM extends DvQuantifiedQLIF {

    public DvQuantifiedIFGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher normalStatus = new DataFetcher<CodePhrase>() {
        @Override
        public CodePhrase get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvQuantity) dataFetchingEnvironment.getSource()).getNormalStatus();
        }
    };

    DataFetcher normalRange = new DataFetcher<DvInterval<DvQuantity>>() {
        @Override
        public DvInterval<DvQuantity> get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvQuantity) dataFetchingEnvironment.getSource()).getNormalRange();
        }
    };

    DataFetcher otherReferenceRange = new DataFetcher<List<ReferenceRange<DvQuantity>>>() {
        @Override
        public List<ReferenceRange<DvQuantity>> get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvQuantity) dataFetchingEnvironment.getSource()).getOtherReferenceRanges();
        }
    };

    DataFetcher magnitudeStatus = new DataFetcher<String>() {
        @Override
        public String get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvQuantity) dataFetchingEnvironment.getSource()).getMagnitudeStatus();
        }
    };

    @Override
    public GraphQLInterfaceType interfaceType(){
        return new QLInterfaceEcisFetch(rmObjectType, super.interfaceType())
                .useDataFetcher(NORMAL_STATUS, normalStatus)
                .useDataFetcher(NORMAL_RANGE, normalRange)
                .useDataFetcher(OTHER_REFERENCE_RANGE, otherReferenceRange)
                .useDataFetcher(MAGNITUDE_STATUS, magnitudeStatus)
                .getInterfaceType();
    }
}
