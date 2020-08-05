package com.ethercis.graphql.datatypes.datavalue.ecis_rm_getter;

import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datatypes.datavalue.DvTextQLType;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.datatypes.text.TermMapping;
import org.openehr.rm.datatypes.uri.DvURI;

import java.util.List;

/**
 * Created by christian on 4/11/2017.
 */
public class DvTextGetEcisRM extends DvTextQLType {

    public DvTextGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher value = new DataFetcher() {
        @Override
        public String get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvText) dataFetchingEnvironment.getSource()).getValue();
        }
    };

    DataFetcher hyperlink = new DataFetcher() {
        @Override
        public DvURI get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvText) dataFetchingEnvironment.getSource()).getHyperlink();
        }
    };

    DataFetcher language = new DataFetcher() {
        @Override
        public CodePhrase get(DataFetchingEnvironment dataFetchingEnvironment) {
            //TODO: what is it supposed to return?
            return ((DvText) dataFetchingEnvironment.getSource()).getLanguage();
        }
    };


    DataFetcher encoding = new DataFetcher() {
        @Override
        public CodePhrase get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvText) dataFetchingEnvironment.getSource()).getEncoding();
        }
    };

    DataFetcher termMapping = new DataFetcher() {
        @Override
        public List<TermMapping> get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvText) dataFetchingEnvironment.getSource()).getMappings();
        }
    };

    @Override
    public GraphQLObjectType objectType() {
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .useDataFetcher(LANGUAGE, language)
                .useDataFetcher(ENCODING, encoding)
                .useDataFetcher(VALUE, value)
                .useDataFetcher(MAPPINGS, termMapping)
                .useDataFetcher(HYPERLINK, hyperlink)
                .getObjectType();
    }
}
