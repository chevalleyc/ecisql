package com.ethercis.graphql.composition.interfaces.ecis_rm_getter;

import com.ethercis.graphql.commons.QLInterfaceEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.composition.interfaces.CareEntryQLIF;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLInterfaceType;
import org.openehr.rm.common.generic.Participation;
import org.openehr.rm.common.generic.PartyProxy;
import org.openehr.rm.composition.content.entry.CareEntry;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.identification.ObjectRef;

import java.util.List;

/**
 * Created by christian on 4/12/2017.
 */
public class CareEntryIFGetEcisRM extends CareEntryQLIF {

    public CareEntryIFGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }


    DataFetcher language = new DataFetcher() {
        @Override
        public CodePhrase get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((CareEntry) dataFetchingEnvironment.getSource()).getLanguage();
        }
    };

    DataFetcher encoding = new DataFetcher() {
        @Override
        public CodePhrase get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((CareEntry) dataFetchingEnvironment.getSource()).getEncoding();
        }
    };

    DataFetcher otherParticipation = new DataFetcher() {
        @Override
        public List<Participation> get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((CareEntry) dataFetchingEnvironment.getSource()).getOtherParticipations();
        }
    };

    DataFetcher workflowId = new DataFetcher() {
        @Override
        public ObjectRef get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((CareEntry) dataFetchingEnvironment.getSource()).getWorkflowId();
        }
    };

    DataFetcher subject = new DataFetcher() {
        @Override
        public PartyProxy get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((CareEntry) dataFetchingEnvironment.getSource()).getSubject();
        }
    };

    DataFetcher provider = new DataFetcher() {
        @Override
        public PartyProxy get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((CareEntry) dataFetchingEnvironment.getSource()).getProvider();
        }
    };

    DataFetcher protocol = new DataFetcher() {
        @Override
        public ItemStructure get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((CareEntry) dataFetchingEnvironment.getSource()).getProtocol();
        }
    };

    DataFetcher guidelineId = new DataFetcher() {
        @Override
        public ObjectRef get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((CareEntry) dataFetchingEnvironment.getSource()).getGuidelineId();
        }
    };


    @Override
    public GraphQLInterfaceType interfaceType() {
        return new QLInterfaceEcisFetch(rmObjectType, super.interfaceType())
                .useDataFetcher(LANGUAGE, language)
                .useDataFetcher(ENCODING, encoding)
                .useDataFetcher(OTHER_PARTICIPATION, otherParticipation)
                .useDataFetcher(WORKFLOW_ID, workflowId)
                .useDataFetcher(SUBJECT, subject)
                .useDataFetcher(PROVIDER, provider)
                .useDataFetcher(PROTOCOL, protocol)
                .useDataFetcher(GUIDELINE_ID, guidelineId)
                .getInterfaceType();
    }
}
