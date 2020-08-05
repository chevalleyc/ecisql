package com.ethercis.graphql.composition.ecis_rm_getter;

import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.composition.EventContextQLType;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.common.generic.Participation;
import org.openehr.rm.common.generic.PartyIdentified;
import org.openehr.rm.composition.EventContext;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.DvCodedText;

import java.util.List;

/**
 * Created by christian on 4/12/2017.
 */
public class EventContextGetEcisRM extends EventContextQLType {

    public EventContextGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher startTime = new DataFetcher() {
        @Override
        public DvDateTime get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((EventContext) dataFetchingEnvironment.getSource()).getStartTime();
        }
    };

    DataFetcher endTime = new DataFetcher() {
        @Override
        public DvDateTime get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((EventContext) dataFetchingEnvironment.getSource()).getEndTime();
        }
    };

    DataFetcher location = new DataFetcher() {
        @Override
        public String get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((EventContext) dataFetchingEnvironment.getSource()).getLocation();
        }
    };

    DataFetcher setting = new DataFetcher() {
        @Override
        public DvCodedText get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((EventContext) dataFetchingEnvironment.getSource()).getSetting();
        }
    };

    DataFetcher otherContext = new DataFetcher() {
        @Override
        public ItemStructure get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((EventContext) dataFetchingEnvironment.getSource()).getOtherContext();
        }
    };

    DataFetcher healthCareFacility = new DataFetcher() {
        @Override
        public PartyIdentified get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((EventContext) dataFetchingEnvironment.getSource()).getHealthCareFacility();
        }
    };

    DataFetcher participation = new DataFetcher() {
        @Override
        public List<Participation> get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((EventContext) dataFetchingEnvironment.getSource()).getParticipations();
        }
    };

    @Override
    public GraphQLObjectType objectType() {
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .useDataFetcher(START_TIME, startTime)
                .useDataFetcher(END_TIME, endTime)
                .useDataFetcher(LOCATION, location)
                .useDataFetcher(SETTING, setting)
                .useDataFetcher(OTHER_CONTEXT, otherContext)
                .useDataFetcher(HEALTH_CARE_FACILITY, healthCareFacility)
                .useDataFetcher(PARTICIPATION, participation)
                .getObjectType();
    }


}
