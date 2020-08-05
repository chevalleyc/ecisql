package com.ethercis.graphql.datastructure.ecis_rm_getter;

import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datastructure.HistoryQLType;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.datastructure.history.Event;
import org.openehr.rm.datastructure.history.History;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;

import java.util.List;

/**
 * Created by christian on 4/12/2017.
 */
public class HistoryGetEcisRM extends HistoryQLType {

    public HistoryGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher originFetcher = new DataFetcher() {
        @Override
        public DvDateTime get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((History) dataFetchingEnvironment.getSource()).getOrigin();
        }
    };

    DataFetcher periodFetcher = new DataFetcher() {
        @Override
        public DvDuration get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((History) dataFetchingEnvironment.getSource()).getPeriod();
        }
    };

    DataFetcher durationFetcher = new DataFetcher() {
        @Override
        public DvDuration get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((History) dataFetchingEnvironment.getSource()).getDuration();
        }
    };

    DataFetcher summaryFetcher = new DataFetcher() {
        @Override
        public ItemStructure get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((History) dataFetchingEnvironment.getSource()).getSummary();
        }
    };

    DataFetcher eventsFetcher = new DataFetcher() {
        @Override
        public List<Event> get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((History) dataFetchingEnvironment.getSource()).getEvents();
        }
    };

    @Override
    public GraphQLObjectType objectType() {
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .useDataFetcher(ORIGIN, originFetcher)
                .useDataFetcher(PERIOD, periodFetcher)
                .useDataFetcher(DURATION, durationFetcher)
                .useDataFetcher(SUMMARY, summaryFetcher)
                .useDataFetcher(EVENTS, eventsFetcher)
                .getObjectType();
    }


}
