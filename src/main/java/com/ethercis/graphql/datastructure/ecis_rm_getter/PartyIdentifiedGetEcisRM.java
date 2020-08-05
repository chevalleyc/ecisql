package com.ethercis.graphql.datastructure.ecis_rm_getter;

import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datastructure.PartyIdentifiedQLType;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.common.generic.PartyIdentified;
import org.openehr.rm.datatypes.basic.DvIdentifier;
import org.openehr.rm.support.identification.PartyRef;

import java.util.List;

/**
 * Created by christian on 4/12/2017.
 */
public class PartyIdentifiedGetEcisRM extends PartyIdentifiedQLType {

    public PartyIdentifiedGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }


    DataFetcher externalRef = new DataFetcher() {
        @Override
        public PartyRef get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((PartyIdentified) dataFetchingEnvironment.getSource()).getExternalRef();
        }
    };

    DataFetcher name = new DataFetcher() {
        @Override
        public String get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((PartyIdentified) dataFetchingEnvironment.getSource()).getName();
        }
    };

    DataFetcher identifiers = new DataFetcher() {
        @Override
        public List<DvIdentifier> get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((PartyIdentified) dataFetchingEnvironment.getSource()).getIdentifiers();
        }
    };

    @Override
    public GraphQLObjectType objectType() {
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .useDataFetcher(EXTERNAL_REF, externalRef)
                .useDataFetcher(NAME, name)
                .useDataFetcher(IDENTIFIERS, identifiers)
                .getObjectType();
    }


}
