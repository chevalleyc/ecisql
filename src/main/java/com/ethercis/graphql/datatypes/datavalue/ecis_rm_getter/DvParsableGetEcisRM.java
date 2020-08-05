package com.ethercis.graphql.datatypes.datavalue.ecis_rm_getter;

import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datatypes.datavalue.DvParsableQLType;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.datatypes.encapsulated.DvParsable;
import org.openehr.rm.datatypes.text.CodePhrase;

/**
 * Created by christian on 4/11/2017.
 */
public class DvParsableGetEcisRM extends DvParsableQLType {

    public DvParsableGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher language = new DataFetcher() {
        @Override
        public CodePhrase get(DataFetchingEnvironment dataFetchingEnvironment) {
            //TODO: what is it supposed to return?
            return ((DvParsable) dataFetchingEnvironment.getSource()).getLanguage();
        }
    };

    DataFetcher charset = new DataFetcher() {
        @Override
        public CodePhrase get(DataFetchingEnvironment dataFetchingEnvironment) {
            //TODO: what is it supposed to return?
            return ((DvParsable) dataFetchingEnvironment.getSource()).getCharset();
        }
    };


    DataFetcher value = new DataFetcher() {
        @Override
        public String get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvParsable) dataFetchingEnvironment.getSource()).getValue();
        }
    };

    DataFetcher formalism = new DataFetcher() {
        @Override
        public String get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvParsable) dataFetchingEnvironment.getSource()).getFormalism();
        }
    };

    @Override
    public GraphQLObjectType objectType() {
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .useDataFetcher(LANGUAGE, language)
                .useDataFetcher(CHARSET, charset)
                .useDataFetcher(VALUE, value)
                .useDataFetcher(FORMALISM, formalism)
                .getObjectType();
    }
}
