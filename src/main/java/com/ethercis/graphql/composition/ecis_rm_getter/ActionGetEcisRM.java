package com.ethercis.graphql.composition.ecis_rm_getter;

import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.composition.ActionQLType;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.composition.content.entry.Action;
import org.openehr.rm.composition.content.entry.ISMTransition;
import org.openehr.rm.composition.content.entry.InstructionDetails;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;

/**
 * Created by christian on 4/12/2017.
 */
public class ActionGetEcisRM extends ActionQLType {

    public ActionGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher time = new DataFetcher<DvDateTime>() {
        @Override
        public DvDateTime get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((Action) dataFetchingEnvironment.getSource()).getTime();
        }
    };

    DataFetcher ismTransition = new DataFetcher<ISMTransition>() {
        @Override
        public ISMTransition get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((Action) dataFetchingEnvironment.getSource()).getIsmTransition();
        }
    };

    DataFetcher instructionDetails = new DataFetcher<InstructionDetails>() {
        @Override
        public InstructionDetails get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((Action) dataFetchingEnvironment.getSource()).getInstructionDetails();
        }
    };

    DataFetcher description = new DataFetcher<ItemStructure>() {
        @Override
        public ItemStructure get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((Action) dataFetchingEnvironment.getSource()).getDescription();
        }
    };

    @Override
    public GraphQLObjectType objectType() {
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .useDataFetcher(TIME, time)
                .useDataFetcher(ISM_TRANSITION, ismTransition)
                .useDataFetcher(INSTRUCTION_DETAILS, instructionDetails)
                .useDataFetcher(DESCRIPTION, description)
                .getObjectType();
    }


}
