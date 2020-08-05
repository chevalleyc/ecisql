package com.ethercis.graphql.datatypes.datavalue.ecis_rm_getter;

import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datatypes.datavalue.DvOrdinalQLType;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.datatypes.quantity.DvOrdinal;
import org.openehr.rm.datatypes.text.DvCodedText;

/**
 * Created by christian on 4/11/2017.
 */
public class DvOrdinalGetEcisRM extends DvOrdinalQLType {

    public DvOrdinalGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher symbol = new DataFetcher() {
        @Override
        public DvCodedText get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvOrdinal) dataFetchingEnvironment.getSource()).getSymbol();
        }
    };

    DataFetcher value = new DataFetcher() {
        @Override
        public Integer get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvOrdinal) dataFetchingEnvironment.getSource()).getValue();
        }
    };

    @Override
    public GraphQLObjectType objectType() {
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .useDataFetcher(VALUE, value)
                .useDataFetcher(SYMBOL, symbol)
                .getObjectType();
    }
}
