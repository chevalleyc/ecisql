package com.ethercis.graphql.datatypes.interfaces.ecis_rm_getter;

import com.ethercis.graphql.commons.QLInterfaceEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datatypes.interfaces.DvOrderedQLIF;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLInterfaceType;
import org.openehr.rm.datatypes.quantity.DvInterval;
import org.openehr.rm.datatypes.quantity.DvOrdered;
import org.openehr.rm.datatypes.quantity.ReferenceRange;
import org.openehr.rm.datatypes.text.CodePhrase;

import java.util.List;

/**
 * Created by christian on 4/11/2017.
 */
public class DvOrderedIFGetEcisRM extends DvOrderedQLIF {

    public DvOrderedIFGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher normalStatus = new DataFetcher() {
        @Override
        public CodePhrase get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvOrdered) dataFetchingEnvironment.getSource()).getNormalStatus();
        }
    };

    DataFetcher normalRange = new DataFetcher() {
        @Override
        public DvInterval get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvOrdered) dataFetchingEnvironment.getSource()).getNormalRange();
        }
    };

    DataFetcher otherReferenceRange = new DataFetcher() {
        @Override
        public List<ReferenceRange> get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvOrdered) dataFetchingEnvironment.getSource()).getOtherReferenceRanges();
        }
    };

    @Override
    public GraphQLInterfaceType interfaceType() {
        return new QLInterfaceEcisFetch(rmObjectType, super.interfaceType())
                .useDataFetcher(NORMAL_STATUS, normalStatus)
                .useDataFetcher(NORMAL_RANGE, normalRange)
                .useDataFetcher(OTHER_REFERENCE_RANGE, otherReferenceRange)
                .getInterfaceType();
    }
}
