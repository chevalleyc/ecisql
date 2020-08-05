package com.ethercis.graphql.change_control.ecis_rm_fetcher;

import com.ethercis.graphql.change_control.ContributionQLType;
import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.common.changecontrol.Contribution;
import org.openehr.rm.common.generic.AuditDetails;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.support.identification.ObjectRef;

import java.util.Set;

/**
 * Created by christian on 4/12/2017.
 */
public class ContributionGetEcisRM extends ContributionQLType {

    public ContributionGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher uid = new DataFetcher<ObjectID>() {
        @Override
        public ObjectID get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((Contribution) dataFetchingEnvironment.getSource()).getUid();
        }
    };

    DataFetcher versions = new DataFetcher<Set<ObjectRef>>() {
        @Override
        public Set<ObjectRef> get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((Contribution) dataFetchingEnvironment.getSource()).getVersions();
        }
    };

    DataFetcher auditDetails = new DataFetcher<AuditDetails>() {
        @Override
        public AuditDetails get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((Contribution) dataFetchingEnvironment.getSource()).getAudit();
        }
    };


    @Override
    public GraphQLObjectType objectType() {
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .useDataFetcher(UID, uid)
                .useDataFetcher(VERSIONS, versions)
                .useDataFetcher(AUDIT, auditDetails)
                .getObjectType();
    }

}
