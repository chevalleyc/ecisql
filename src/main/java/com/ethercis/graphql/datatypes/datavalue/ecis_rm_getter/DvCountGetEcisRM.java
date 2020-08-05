package com.ethercis.graphql.datatypes.datavalue.ecis_rm_getter;

import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datatypes.datavalue.DvCountQLType;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.datatypes.quantity.DvCount;

/**
 * Created by christian on 4/11/2017.
 */
public class DvCountGetEcisRM extends DvCountQLType {

    public DvCountGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher magnitudeFetcher = new DataFetcher() {
        @Override
        public Integer get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvCount) dataFetchingEnvironment.getSource()).getMagnitude();
        }
    };

    @Override
    public GraphQLObjectType objectType() {
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .useDataFetcher(MAGNITUDE, magnitudeFetcher)
                .getObjectType();
    }
}
