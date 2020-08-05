package com.ethercis.graphql.composition.ecis_rm_getter;

import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.composition.InstructionQLType;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.composition.content.entry.Activity;
import org.openehr.rm.composition.content.entry.Instruction;
import org.openehr.rm.datatypes.encapsulated.DvParsable;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.DvText;

import java.util.List;

/**
 * Created by christian on 4/12/2017.
 */
public class InstructionGetEcisRM extends InstructionQLType {

    public InstructionGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher narrative = new DataFetcher<DvText>() {
        @Override
        public DvText get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((Instruction) dataFetchingEnvironment.getSource()).getNarrative();
        }
    };

    DataFetcher expiryTime = new DataFetcher<DvDateTime>() {
        @Override
        public DvDateTime get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((Instruction) dataFetchingEnvironment.getSource()).getExpiryTime();
        }
    };

    DataFetcher wfDefinition = new DataFetcher<DvParsable>() {
        @Override
        public DvParsable get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((Instruction) dataFetchingEnvironment.getSource()).getWfDefinition();
        }
    };

    DataFetcher activities = new DataFetcher<List<Activity>>() {
        @Override
        public List<Activity> get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((Instruction) dataFetchingEnvironment.getSource()).getActivities();
        }
    };

    @Override
    public GraphQLObjectType objectType() {
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .useDataFetcher(NARRATIVE, narrative)
                .useDataFetcher(EXPIRY_TIME, expiryTime)
                .useDataFetcher(WF_DEFINITION, wfDefinition)
                .useDataFetcher(ACTIVITIES, activities)
                .getObjectType();
    }


}
