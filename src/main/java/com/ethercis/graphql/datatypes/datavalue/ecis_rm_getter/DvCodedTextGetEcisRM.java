package com.ethercis.graphql.datatypes.datavalue.ecis_rm_getter;

import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datatypes.datavalue.DvCodedTextQLType;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.TermMapping;
import org.openehr.rm.datatypes.uri.DvURI;

import java.util.List;

/**
 * Created by christian on 4/11/2017.
 */
public class DvCodedTextGetEcisRM extends DvCodedTextQLType {

    public DvCodedTextGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher language = new DataFetcher() {
        @Override
        public CodePhrase get(DataFetchingEnvironment dataFetchingEnvironment) {
            //TODO: what is it supposed to return?
            return ((DvCodedText) dataFetchingEnvironment.getSource()).getLanguage();
        }
    };


    DataFetcher encoding = new DataFetcher() {
        @Override
        public CodePhrase get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvCodedText) dataFetchingEnvironment.getSource()).getEncoding();
        }
    };


    DataFetcher definingCode = new DataFetcher() {
        @Override
        public CodePhrase get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvCodedText) dataFetchingEnvironment.getSource()).getDefiningCode();
        }
    };

    DataFetcher value = new DataFetcher() {
        @Override
        public String get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvCodedText) dataFetchingEnvironment.getSource()).getValue();
        }
    };

    DataFetcher termMapping = new DataFetcher() {
        @Override
        public List<TermMapping> get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvCodedText) dataFetchingEnvironment.getSource()).getMappings();
        }
    };

    DataFetcher hyperlink = new DataFetcher() {
        @Override
        public DvURI get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvCodedText) dataFetchingEnvironment.getSource()).getHyperlink();
        }
    };

    @Override
    public GraphQLObjectType objectType() {
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .useDataFetcher(LANGUAGE, language)
                .useDataFetcher(ENCODING, encoding)
                .useDataFetcher(DEFINING_CODE, definingCode)
                .useDataFetcher(VALUE, value)
                .useDataFetcher(MAPPINGS, termMapping)
                .useDataFetcher(HYPERLINK, hyperlink)
                .getObjectType();
    }
}
