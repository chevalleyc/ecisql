package com.ethercis.graphql.composition.ecis_rm_getter;

import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.composition.ObservationQLType;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.composition.content.entry.Observation;
import org.openehr.rm.datastructure.history.History;

/**
 * Created by christian on 4/12/2017.
 */
public class ObservationGetEcisRM extends ObservationQLType {

    public ObservationGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher stateFetcher = new DataFetcher() {
        @Override
        public History get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((Observation) dataFetchingEnvironment.getSource()).getState();
        }
    };

    DataFetcher dataFetcher = new DataFetcher() {
        @Override
        public History get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((Observation) dataFetchingEnvironment.getSource()).getData();
        }
    };

    @Override
    public GraphQLObjectType objectType() {
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .useDataFetcher(STATE, stateFetcher)
                .useDataFetcher(DATA, dataFetcher)
                .getObjectType();
    }


}
