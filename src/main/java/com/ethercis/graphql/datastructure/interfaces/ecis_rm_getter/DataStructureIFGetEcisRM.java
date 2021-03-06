package com.ethercis.graphql.datastructure.interfaces.ecis_rm_getter;

import com.ethercis.graphql.commons.QLInterfaceEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datastructure.interfaces.DataStructureQLIF;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLInterfaceType;
import org.openehr.rm.datastructure.DataStructure;
import org.openehr.rm.datatypes.text.DvText;

/**
 * Created by christian on 4/12/2017.
 */
public class DataStructureIFGetEcisRM extends DataStructureQLIF {

    public DataStructureIFGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
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
    public GraphQLInterfaceType interfaceType() {
        return new QLInterfaceEcisFetch(rmObjectType, super.interfaceType())
                .useDataFetcher(NAME, nameFetcher)
                .useDataFetcher(ARCHETYPE_NODE_ID, archetypeNodeIdFetcher)
                .getInterfaceType();
    }
}
