package com.ethercis.graphql.datastructure.interfaces.ecis_rm_getter;

import com.ethercis.ehr.encode.wrappers.element.ElementWrapper;
import com.ethercis.graphql.commons.QLInterfaceEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datastructure.interfaces.ItemQLIF;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLInterfaceType;
import org.openehr.rm.datatypes.text.DvText;

/**
 * Created by christian on 4/12/2017.
 */
public class ItemIFGetEcisRM extends ItemQLIF {

    public ItemIFGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher name = new DataFetcher() {
        @Override
        public DvText get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((ElementWrapper) dataFetchingEnvironment.getSource()).getName();
        }
    };


    DataFetcher archetypeNodeId = new DataFetcher() {
        @Override
        public String get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((ElementWrapper) dataFetchingEnvironment.getSource()).getArchetypeNodeId();
        }
    };

    @Override
    public GraphQLInterfaceType interfaceType() {
        return new QLInterfaceEcisFetch(rmObjectType, super.interfaceType())
                .useDataFetcher(NAME, name)
                .useDataFetcher(ARCHETYPE_NODE_ID, archetypeNodeId)
                .getInterfaceType();
    }
}
