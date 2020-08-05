package com.ethercis.graphql.datastructure.ecis_rm_getter;

import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datastructure.EventQLType;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.datastructure.history.Event;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;

/**
 * Created by christian on 4/12/2017.
 */
public class EventGetEcisRM extends EventQLType {

    public EventGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher timeFetcher = new DataFetcher() {
        @Override
        public DvDateTime get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((Event) dataFetchingEnvironment.getSource()).getTime();
        }
    };

    DataFetcher stateFetcher = new DataFetcher<ItemStructure>() {
        @Override
        public ItemStructure get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((Event) dataFetchingEnvironment.getSource()).getState();
        }
    };

    DataFetcher dataFetcher = new DataFetcher<ItemStructure>() {
        @Override
        public ItemStructure get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((Event) dataFetchingEnvironment.getSource()).getData();
        }
    };
    @Override
    public GraphQLObjectType objectType() {
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .useDataFetcher(TIME, timeFetcher)
                .useDataFetcher(STATE, stateFetcher)
                .useDataFetcher(DATA, dataFetcher)
                .getObjectType();
    }


}
