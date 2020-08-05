package com.ethercis.graphql.datastructure.ecis_rm_getter;

import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datastructure.ParticipationQLType;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.common.generic.Participation;
import org.openehr.rm.common.generic.PartyIdentified;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;

/**
 * Created by christian on 4/12/2017.
 */
public class ParticipationGetEcisRM extends ParticipationQLType {

    public ParticipationGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }


    DataFetcher function = new DataFetcher() {
        @Override
        public DvText get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((Participation) dataFetchingEnvironment.getSource()).getFunction();
        }
    };

    DataFetcher mode = new DataFetcher() {
        @Override
        public DvCodedText get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((Participation) dataFetchingEnvironment.getSource()).getMode();
        }
    };

    DataFetcher performer = new DataFetcher() {
        @Override
        public PartyIdentified get(DataFetchingEnvironment dataFetchingEnvironment) {
            return (PartyIdentified) ((Participation) dataFetchingEnvironment.getSource()).getPerformer();
        }
    };

    //TODO: fetcher for date/time

    @Override
    public GraphQLObjectType objectType() {
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .useDataFetcher(FUNCTION, function)
                .useDataFetcher(MODE, mode)
                .useDataFetcher(PERFORMER, performer)
                .getObjectType();
    }


}
