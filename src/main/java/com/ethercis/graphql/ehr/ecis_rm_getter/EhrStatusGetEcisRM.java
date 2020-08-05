package com.ethercis.graphql.ehr.ecis_rm_getter;

import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.ehr.EhrStatusQLType;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.common.generic.PartySelf;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.ehr.EHRStatus;

/**
 * Created by christian on 4/12/2017.
 */
public class EhrStatusGetEcisRM extends EhrStatusQLType {

    public EhrStatusGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher subject = new DataFetcher<PartySelf>() {
        @Override
        public PartySelf get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((EHRStatus) dataFetchingEnvironment.getSource()).getSubject();
        }
    };

    DataFetcher otherDetails = new DataFetcher<ItemStructure>() {
        @Override
        public ItemStructure get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((EHRStatus) dataFetchingEnvironment.getSource()).getOtherDetails();
        }
    };


    @Override
    public GraphQLObjectType objectType() {
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .useDataFetcher(SUBJECT, subject)
                .useDataFetcher(OTHER_DETAILS, otherDetails)
                .getObjectType();
    }

}
