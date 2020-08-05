package com.ethercis.graphql.datatypes.interfaces.ecis_rm_getter;

import com.ethercis.graphql.commons.QLInterfaceEcisFetch;
import com.ethercis.graphql.commons.RmObjectQLRegistry;
import com.ethercis.graphql.datatypes.interfaces.DvTemporalQLIF;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLInterfaceType;
import org.openehr.rm.datatypes.quantity.datetime.DvTemporal;
import org.openehr.rm.datatypes.text.CodePhrase;

/**
 * Created by christian on 4/11/2017.
 */
public class DvTemporalIFGetEcisRM extends DvTemporalQLIF {

    public DvTemporalIFGetEcisRM(RmObjectQLRegistry rmObjectQLRegistry) {
        super(rmObjectQLRegistry);
    }

    DataFetcher normalStatus = new DataFetcher() {
        @Override
        public CodePhrase get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvTemporal) dataFetchingEnvironment.getSource()).getNormalStatus();
        }
    };

    DataFetcher magnitudeStatus = new DataFetcher() {
        @Override
        public String get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvTemporal) dataFetchingEnvironment.getSource()).getMagnitudeStatus();
        }
    };

    @Override
    public GraphQLInterfaceType interfaceType() {
        return new QLInterfaceEcisFetch(rmObjectType, super.interfaceType())
                .useDataFetcher(NORMAL_STATUS, normalStatus)
                .useDataFetcher(MAGNITUDE_STATUS, magnitudeStatus)
                .getInterfaceType();
    }
}
