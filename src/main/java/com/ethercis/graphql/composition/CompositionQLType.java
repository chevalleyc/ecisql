package com.ethercis.graphql.composition;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.*;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/13/2017.
 */
public class CompositionQLType extends EcisQLType {
    public static final String NAME = "name";
    public static final String ARCHETYPE_NODE_ID = "archetype_node_id";
    public static final String UID = "uid";
    public static final String LINKS = "links";
    public static final String ARCHETYPE_DETAILS = "archetype_details";
    public static final String LANGUAGE = "language";
    public static final String TERRITORY = "territory";
    public static final String CATEGORY = "category";
    public static final String CONTEXT = "context";
    public static final String COMPOSER = "composer";
    public static final String CONTENT = "content";
    private final String id = I_RmObjectQLRegistry.COMPOSITION;

    protected CompositionQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }


    //TODO: support interval!
    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType objectType = newObject()
                .name(id)
                .description("The abstract parent of all ENTRY subtypes")
                .field(
                        newFieldDefinition()
                                .name(NAME)
                                .description("Runtime name of this fragment")
//                                .dataFetcher(name)
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.DV_TEXT)))
                )
                .field(
                        newFieldDefinition()
                                .name(ARCHETYPE_NODE_ID)
                                .description("Design-time archetype id of this node taken from its generating archetype")
//                                .dataFetcher(archetypeNodeId)
                                .type(new GraphQLNonNull(GraphQLString))
                )
                .field(
                        newFieldDefinition()
                                .name(UID)
                                .description("Optional globally unique object identifier for root points of archetyped structures")
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.UID))
                )
                .field(
                        newFieldDefinition()
                                .name(LINKS)
//                                .dataFetcher(links)
                                .description("Links to other archetyped structures (data whose root object inherits from ARCHETYPED, such as ENTRY, SECTION and so on)")
                                .type(new GraphQLList(rmObjectType.objectType(I_RmObjectQLRegistry.LINK)))
                )
                .field(
                        newFieldDefinition()
                                .name(ARCHETYPE_DETAILS)
                                .description("Details of archetyping used on this node")
//                                .dataFetcher(archetypeDetails)
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.ARCHETYPED))
                )
                .field(
                        newFieldDefinition()
                                .name(LANGUAGE)
                                .description("Mandatory indicator of the localised language in which this Entry is written")
//                                .dataFetcher(language)
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.CODE_PHRASE)))
                )
                .field(
                        newFieldDefinition()
                                .name(TERRITORY)
                                .description("Name of territory in which this Composition was written")
//                                .dataFetcher(territory)
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.CODE_PHRASE)))
                )
                .field(
                        newFieldDefinition()
                                .name(CATEGORY)
                                .description("Indicates what broad category this Composition is belongs")
//                                .dataFetcher(category)
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.DV_CODED_TEXT)))
                )
                .field(
                        newFieldDefinition()
                                .name(CONTEXT)
                                .description("The clinical session context of this Composition")
//                                .dataFetcher(context)
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.EVENT_CONTEXT))
                )
                .field(
                        newFieldDefinition()
                                .name(COMPOSER)
                                .description("The person primarily responsible for the content of the Composition")
//                                .dataFetcher(composer)
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.PARTY_PROXY)))
                )
                .field(
                        newFieldDefinition()
                                .name(CONTENT)
                                .description("The content of this Composition")
                                .argument(rmObjectType.argument(I_RmObjectQLRegistry.ARCHETYPE_NODE_ID_ARGUMENT))
                                .argument(rmObjectType.argument(I_RmObjectQLRegistry.NODE_NAME_ARGUMENT))
//                                .dataFetcher(content)
                                .type(new GraphQLList(rmObjectType.unionType(I_RmObjectQLRegistry.CONTENT_ITEM)))
                )
                .build();
        return objectType;
    }

    @Override
    public String id() {
        return id;
    }
}
