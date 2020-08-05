package com.ethercis.graphql.datastructure.interfaces.ecis_rm_getter;

import com.ethercis.graphql.commons.QLInterfaceEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datastructure.interfaces.ItemStructureQLIF;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLInterfaceType;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.support.identification.UIDBasedID;

import java.util.Set;

/**
 * Created by christian on 4/12/2017.
 */
public class ItemStructureIFGetEcisRM extends ItemStructureQLIF {

    public ItemStructureIFGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher linksFetcher = new DataFetcher() {
        @Override
        public Set<Link> get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((ItemStructure) dataFetchingEnvironment.getSource()).getLinks();
        }
    };

    DataFetcher uidFetcher = new DataFetcher() {
        @Override
        public UIDBasedID get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((ItemStructure) dataFetchingEnvironment.getSource()).getUid();
        }
    };

    DataFetcher archetypeDetailsFetcher = new DataFetcher() {
        @Override
        public Archetyped get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((ItemStructure) dataFetchingEnvironment.getSource()).getArchetypeDetails();
        }
    };

    @Override
    public GraphQLInterfaceType interfaceType() {
        return new QLInterfaceEcisFetch(rmObjectType, super.interfaceType())
                .useDataFetcher(LINKS, linksFetcher)
                .useDataFetcher(UID, uidFetcher)
                .useDataFetcher(ARCHETYPE_DETAILS, archetypeDetailsFetcher)
                .getInterfaceType();
    }


}
