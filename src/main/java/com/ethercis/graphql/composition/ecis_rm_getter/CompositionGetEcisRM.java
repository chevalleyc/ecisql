package com.ethercis.graphql.composition.ecis_rm_getter;

import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.composition.CompositionQLType;
import com.ethercis.graphql.datastructure.arguments.NodePredicate;
import com.ethercis.graphql.datastructure.interfaces.Arguments;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.generic.PartyProxy;
import org.openehr.rm.composition.Composition;
import org.openehr.rm.composition.EventContext;
import org.openehr.rm.composition.content.ContentItem;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by christian on 4/12/2017.
 */
public class CompositionGetEcisRM extends CompositionQLType {

    public CompositionGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher name = new DataFetcher() {
        @Override
        public DvText get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((Composition) dataFetchingEnvironment.getSource()).getName();
        }
    };

    DataFetcher archetypeNodeId = new DataFetcher() {
        @Override
        public String get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((Composition) dataFetchingEnvironment.getSource()).getArchetypeNodeId();
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
            return ((Composition) dataFetchingEnvironment.getSource()).getLinks();
        }
    };

    DataFetcher archetypeDetails = new DataFetcher() {
        @Override
        public Archetyped get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((Composition) dataFetchingEnvironment.getSource()).getArchetypeDetails();
        }
    };

    DataFetcher language = new DataFetcher() {
        @Override
        public CodePhrase get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((Composition) dataFetchingEnvironment.getSource()).getLanguage();
        }
    };

    DataFetcher territory = new DataFetcher() {
        @Override
        public CodePhrase get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((Composition) dataFetchingEnvironment.getSource()).getTerritory();
        }
    };

    DataFetcher category = new DataFetcher() {
        @Override
        public DvCodedText get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((Composition) dataFetchingEnvironment.getSource()).getCategory();
        }
    };

    DataFetcher context = new DataFetcher() {
        @Override
        public EventContext get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((Composition) dataFetchingEnvironment.getSource()).getContext();
        }
    };

    DataFetcher composer = new DataFetcher() {
        @Override
        public PartyProxy get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((Composition) dataFetchingEnvironment.getSource()).getComposer();
        }
    };

    DataFetcher content = new DataFetcher() {
        @Override
        public List<ContentItem> get(DataFetchingEnvironment dataFetchingEnvironment) {
            Map<String, Object> arguments = dataFetchingEnvironment.getArguments();

            Object source = dataFetchingEnvironment.getSource();
            if (source instanceof Composition) {
                List<ContentItem> contents = ((Composition) source).getContent();
                if (new Arguments(arguments).hasSetArguments()){
                    List<ContentItem> filteredContent = new ArrayList<>();
                    for (ContentItem contentItem: contents){
                        if (!new NodePredicate(arguments).match(contentItem))
                            continue;
                        filteredContent.add(contentItem);
                    }
                    return filteredContent;
                }
                return contents;
            }
            return null;
        }
    };

    @Override
    public GraphQLObjectType objectType() {
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .useDataFetcher(NAME, name)
                .useDataFetcher(ARCHETYPE_NODE_ID, archetypeNodeId)
                .useDataFetcher(LINKS, links)
                .useDataFetcher(ARCHETYPE_DETAILS, archetypeDetails)
                .useDataFetcher(LANGUAGE, language)
                .useDataFetcher(TERRITORY, territory)
                .useDataFetcher(CATEGORY, category)
                .useDataFetcher(CONTEXT, context)
                .useDataFetcher(COMPOSER, composer)
                .useDataFetcher(CONTENT, content)
                .getObjectType();
    }


}
