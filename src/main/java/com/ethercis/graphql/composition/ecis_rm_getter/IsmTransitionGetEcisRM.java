package com.ethercis.graphql.composition.ecis_rm_getter;

import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.composition.IsmTransitionQLType;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.composition.content.entry.ISMTransition;
import org.openehr.rm.datatypes.text.DvCodedText;

/**
 * Created by christian on 4/12/2017.
 */
public class IsmTransitionGetEcisRM extends IsmTransitionQLType {

    public IsmTransitionGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher currentState = new DataFetcher<DvCodedText>() {
        @Override
        public DvCodedText get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((ISMTransition) dataFetchingEnvironment.getSource()).getCurrentState();
        }
    };

    DataFetcher transition = new DataFetcher<DvCodedText>() {
        @Override
        public DvCodedText get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((ISMTransition) dataFetchingEnvironment.getSource()).getTransition();
        }
    };

    DataFetcher careflowStep = new DataFetcher<DvCodedText>() {
        @Override
        public DvCodedText get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((ISMTransition) dataFetchingEnvironment.getSource()).getCareflowStep();
        }
    };

    @Override
    public GraphQLObjectType objectType() {
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .useDataFetcher(CURRENT_STATE, currentState)
                .useDataFetcher(TRANSITION, transition)
                .useDataFetcher(CAREFLOW_STEP, careflowStep)
                .getObjectType();
    }


}
