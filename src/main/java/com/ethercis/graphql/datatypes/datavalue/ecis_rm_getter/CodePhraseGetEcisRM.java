package com.ethercis.graphql.datatypes.datavalue.ecis_rm_getter;

import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datatypes.datavalue.CodePhraseQLType;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.identification.TerminologyID;

/**
 * Created by christian on 4/11/2017.
 */
public class CodePhraseGetEcisRM extends CodePhraseQLType {

    public CodePhraseGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher terminologyID = new DataFetcher() {
        @Override
        public TerminologyID get(DataFetchingEnvironment dataFetchingEnvironment) {
            //TODO: what is it supposed to return?
            return ((CodePhrase) dataFetchingEnvironment.getSource()).getTerminologyId();
        }
    };

    DataFetcher codeString = new DataFetcher() {
        @Override
        public String get(DataFetchingEnvironment dataFetchingEnvironment) {
            //TODO: what is it supposed to return?
            return ((CodePhrase) dataFetchingEnvironment.getSource()).getCodeString();
        }
    };

    @Override
    public GraphQLObjectType objectType() {
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .useDataFetcher(TERMINOLOGY_ID, terminologyID)
                .useDataFetcher(CODE_STRING, codeString)
                .getObjectType();
    }
}
