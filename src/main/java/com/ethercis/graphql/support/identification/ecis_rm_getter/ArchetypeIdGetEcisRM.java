package com.ethercis.graphql.support.identification.ecis_rm_getter;

import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.support.identification.ArchetypeIdQLType;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.support.identification.ArchetypeID;

/**
 * Created by christian on 4/11/2017.
 */
public class ArchetypeIdGetEcisRM extends ArchetypeIdQLType {

    public ArchetypeIdGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher value = new DataFetcher() {
        @Override
        public String get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((ArchetypeID)dataFetchingEnvironment.getSource()).getValue();
        }
    };

    @Override
    public GraphQLObjectType objectType(){
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .useDataFetcher(VALUE, value)
                .getObjectType();
    }
}
