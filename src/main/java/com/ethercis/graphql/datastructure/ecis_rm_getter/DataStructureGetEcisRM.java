package com.ethercis.graphql.datastructure.ecis_rm_getter;

import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datastructure.interfaces.DataStructureQLType;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.datastructure.DataStructure;
import org.openehr.rm.datatypes.text.DvText;

/**
 * Created by christian on 4/12/2017.
 */
public class DataStructureGetEcisRM extends DataStructureQLType {

    public DataStructureGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher nameFetcher = new DataFetcher() {
        @Override
        public DvText get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DataStructure) dataFetchingEnvironment.getSource()).getName();
        }
    };

    DataFetcher archetypeNodeIdFetcher = new DataFetcher() {
        @Override
        public String get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DataStructure) dataFetchingEnvironment.getSource()).getArchetypeNodeId();
        }
    };

    @Override
    public GraphQLObjectType objectType() {
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .useDataFetcher(NAME, nameFetcher)
                .useDataFetcher(ARCHETYPE_NODE_ID, archetypeNodeIdFetcher)
                .getObjectType();
    }
}
