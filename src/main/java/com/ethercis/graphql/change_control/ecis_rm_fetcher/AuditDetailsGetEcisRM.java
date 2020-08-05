package com.ethercis.graphql.change_control.ecis_rm_fetcher;

import com.ethercis.graphql.change_control.AuditDetailsQLType;
import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.common.generic.AuditDetails;
import org.openehr.rm.common.generic.PartyProxy;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;

/**
 * Created by christian on 4/12/2017.
 */
public class AuditDetailsGetEcisRM extends AuditDetailsQLType {

    public AuditDetailsGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher systemId = new DataFetcher<String>() {
        @Override
        public String get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((AuditDetails) dataFetchingEnvironment.getSource()).getSystemId();
        }
    };

    DataFetcher timeCommitted = new DataFetcher<DvDateTime>() {
        @Override
        public DvDateTime get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((AuditDetails) dataFetchingEnvironment.getSource()).getTimeCommitted();
        }
    };

    DataFetcher changeType = new DataFetcher<DvCodedText>() {
        @Override
        public DvCodedText get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((AuditDetails) dataFetchingEnvironment.getSource()).getChangeType();
        }
    };

    DataFetcher description = new DataFetcher<DvText>() {
        @Override
        public DvText get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((AuditDetails) dataFetchingEnvironment.getSource()).getDescription();
        }
    };

    DataFetcher committer = new DataFetcher<PartyProxy>() {
        @Override
        public PartyProxy get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((AuditDetails) dataFetchingEnvironment.getSource()).getCommitter();
        }
    };

    @Override
    public GraphQLObjectType objectType() {
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .useDataFetcher(SYSTEM_ID, systemId)
                .useDataFetcher(TIME_COMMITTED, timeCommitted)
                .useDataFetcher(CHANGE_TYPE, changeType)
                .useDataFetcher(DESCRIPTION, description)
                .useDataFetcher(COMMITTER, committer)
                .getObjectType();
    }

}
