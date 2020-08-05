package com.ethercis.graphql.datatypes.datavalue.ecis_rm_getter;

import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datatypes.datavalue.DvIdentifierQLType;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.datatypes.basic.DvIdentifier;

/**
 * Created by christian on 4/11/2017.
 */
public class DvIdentifierGetEcisRM extends DvIdentifierQLType {

    public DvIdentifierGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }


    DataFetcher issuer = new DataFetcher() {
        @Override
        public String get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvIdentifier) dataFetchingEnvironment.getSource()).getIssuer();
        }
    };

    DataFetcher assigner = new DataFetcher() {
        @Override
        public String get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvIdentifier) dataFetchingEnvironment.getSource()).getAssigner();
        }
    };
    DataFetcher identifer = new DataFetcher() {
        @Override
        public String get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvIdentifier) dataFetchingEnvironment.getSource()).getId();
        }
    };
    DataFetcher type = new DataFetcher() {
        @Override
        public String get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvIdentifier) dataFetchingEnvironment.getSource()).getType();
        }
    };

    @Override
    public GraphQLObjectType objectType() {
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .useDataFetcher(ISSUER, issuer)
                .useDataFetcher(ASSIGNER, assigner)
                .useDataFetcher(ID, identifer)
                .useDataFetcher(TYPE, type)
                .getObjectType();
    }
}
