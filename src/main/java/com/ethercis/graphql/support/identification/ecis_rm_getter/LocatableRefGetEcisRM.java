package com.ethercis.graphql.support.identification.ecis_rm_getter;

import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.support.identification.LocatableRefQLType;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.support.identification.LocatableRef;
import org.openehr.rm.support.identification.ObjectID;

/**
 * Created by christian on 4/12/2017.
 */
public class LocatableRefGetEcisRM extends LocatableRefQLType {

    public LocatableRefGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher id = new DataFetcher<ObjectID>() {
        @Override
        public ObjectID get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((LocatableRef) dataFetchingEnvironment.getSource()).getId();
        }
    };

    @Override
    public GraphQLObjectType objectType() {
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .useDataFetcher(ID, id)
                .getObjectType();
    }


}
