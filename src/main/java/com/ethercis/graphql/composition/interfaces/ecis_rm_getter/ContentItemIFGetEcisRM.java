package com.ethercis.graphql.composition.interfaces.ecis_rm_getter;

import com.ethercis.graphql.commons.QLInterfaceEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.composition.interfaces.ContentItemQLIF;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLInterfaceType;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.composition.content.ContentItem;
import org.openehr.rm.datatypes.text.DvText;

import java.util.Set;

/**
 * Created by christian on 4/12/2017.
 */
public class ContentItemIFGetEcisRM extends ContentItemQLIF {

    public ContentItemIFGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher name = new DataFetcher() {
        @Override
        public DvText get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((ContentItem) dataFetchingEnvironment.getSource()).getName();
        }
    };

    DataFetcher archetypeNodeId = new DataFetcher() {
        @Override
        public String get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((ContentItem) dataFetchingEnvironment.getSource()).getArchetypeNodeId();
        }
    };

//    DataFetcher uid = new DataFetcher() {
//        @Override
//        public  get(DataFetchingEnvironment dataFetchingEnvironment) {
//            return ((ContentItem) dataFetchingEnvironment.getSource()).getArchetypeNodeId();
//        }
//    };

    DataFetcher links = new DataFetcher() {
        @Override
        public Set<Link> get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((ContentItem) dataFetchingEnvironment.getSource()).getLinks();
        }
    };

    DataFetcher archetypeDetails = new DataFetcher() {
        @Override
        public Archetyped get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((ContentItem) dataFetchingEnvironment.getSource()).getArchetypeDetails();
        }
    };

    @Override
    public GraphQLInterfaceType interfaceType() {
        return new QLInterfaceEcisFetch(rmObjectType, super.interfaceType())
                .useDataFetcher(NAME, name)
                .useDataFetcher(ARCHETYPE_NODE_ID, archetypeNodeId)
                .useDataFetcher(LINKS, links)
                .useDataFetcher(ARCHETYPE_DETAILS, archetypeDetails)
                .getInterfaceType();

    }
}
