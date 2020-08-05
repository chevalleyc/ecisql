package com.ethercis.graphql.composition.ecis_rm_getter;

import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.composition.ActivityQLType;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.composition.content.entry.Activity;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.encapsulated.DvParsable;

/**
 * Created by christian on 4/12/2017.
 */
public class ActivityGetEcisRM extends ActivityQLType {

    public ActivityGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher timing = new DataFetcher<DvParsable>() {
        @Override
        public DvParsable get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((Activity) dataFetchingEnvironment.getSource()).getTiming();
        }
    };

    DataFetcher actionArchetypeId = new DataFetcher<String>() {
        @Override
        public String get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((Activity) dataFetchingEnvironment.getSource()).getActionArchetypeId();
        }
    };

    DataFetcher description = new DataFetcher<ItemStructure>() {
        @Override
        public ItemStructure get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((Activity) dataFetchingEnvironment.getSource()).getDescription();
        }
    };

    @Override
    public GraphQLObjectType objectType() {
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .useDataFetcher(TIMING, timing)
                .useDataFetcher(ACTION_ARCHETYPE_ID, actionArchetypeId)
                .useDataFetcher(DESCRIPTION, description)
                .getObjectType();
    }


}
