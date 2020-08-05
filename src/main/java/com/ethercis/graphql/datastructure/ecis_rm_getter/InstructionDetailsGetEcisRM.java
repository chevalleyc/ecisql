package com.ethercis.graphql.datastructure.ecis_rm_getter;

import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datastructure.InstructionDetailsQLType;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.composition.content.entry.InstructionDetails;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.support.identification.LocatableRef;

/**
 * Created by christian on 4/12/2017.
 */
public class InstructionDetailsGetEcisRM extends InstructionDetailsQLType {

    public InstructionDetailsGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher instructionId = new DataFetcher<LocatableRef>() {
        @Override
        public LocatableRef get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((InstructionDetails) dataFetchingEnvironment.getSource()).getInstructionId();
        }
    };

    DataFetcher activityId = new DataFetcher<String>() {
        @Override
        public String get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((InstructionDetails) dataFetchingEnvironment.getSource()).getActivityId();
        }
    };

    DataFetcher wfDetails = new DataFetcher<ItemStructure>() {
        @Override
        public ItemStructure get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((InstructionDetails) dataFetchingEnvironment.getSource()).getWfDetails();
        }
    };

    @Override
    public GraphQLObjectType objectType() {
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .useDataFetcher(INSTRUCTION_ID, instructionId)
                .useDataFetcher(ACTIVITY_ID, activityId)
                .useDataFetcher(WF_DETAILS, wfDetails)
                .getObjectType();
    }


}
