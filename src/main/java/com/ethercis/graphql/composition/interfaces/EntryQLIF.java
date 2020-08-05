package com.ethercis.graphql.composition.interfaces;

import com.ethercis.graphql.commons.EcisQLInterfaceType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.*;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLInterfaceType.newInterface;

/**
 * Created by christian on 3/13/2017.
 */
public class EntryQLIF extends EcisQLInterfaceType {
        public static final String NAME = "name";
        public static final String ARCHETYPE_NODE_ID = "archetype_node_id";
        public static final String UID = "uid";
        public static final String LINKS = "links";
        public static final String ARCHETYPE_DETAILS = "archetype_details";
        public static final String LANGUAGE = "language";
        public static final String ENCODING = "encoding";
        public static final String OTHER_PARTICIPATION = "other_participation";
        public static final String WORKFLOW_ID = "workflow_id";
        public static final String SUBJECT = "subject";
        public static final String PROVIDER = "provider";
        private final String id = I_RmObjectQLRegistry.ENTRY;

    protected EntryQLIF(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    //TODO: support interval!
    @Override
    public GraphQLInterfaceType interfaceType() {
        GraphQLInterfaceType graphQLInterfaceType = newInterface()
                .name(id)
                .description("The abstract parent of all ENTRY subtypes")
                .field(
                        newFieldDefinition()
                                .name(NAME)
                                .description("Runtime name of this fragment")
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.DV_TEXT)))
                )
                .field(
                        newFieldDefinition()
                                .name(ARCHETYPE_NODE_ID)
                                .description("Design-time archetype id of this node taken from its generating archetype")
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
                                .description("Links to other archetyped structures (data whose root object inherits from ARCHETYPED, such as ENTRY, SECTION and so on)")
                                .type(new GraphQLList(rmObjectType.objectType(I_RmObjectQLRegistry.LINK)))
                )
                .field(
                        newFieldDefinition()
                                .name(ARCHETYPE_DETAILS)
                                .description("Details of archetyping used on this node")
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.ARCHETYPED))
                )
                .field(
                        newFieldDefinition()
                                .name(LANGUAGE)
                                .description("Mandatory indicator of the localised language in which this Entry is written")
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.CODE_PHRASE)))
                )
                .field(
                        newFieldDefinition()
                                .name(ENCODING)
                                .description("Name of character set in which text values in this Entry are encoded")
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.CODE_PHRASE)))
                )
                .field(
                        newFieldDefinition()
                                .name(OTHER_PARTICIPATION)
                                .description("Other participations at ENTRY level")
                                .type(new GraphQLList(rmObjectType.objectType(I_RmObjectQLRegistry.PARTICIPATION)))
                )
                .field(
                        newFieldDefinition()
                                .name(WORKFLOW_ID)
                                .description("dentifier of externally held workflow engine data for this workflow execution")
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.OBJECT_REF))
                )
                .field(
                        newFieldDefinition()
                                .name(SUBJECT)
                                .description("Id of human subject of this ENTRY")
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.PARTY_PROXY)))
                )
                .field(
                        newFieldDefinition()
                                .name(PROVIDER)
                                .description("Optional identification of provider of the information in this ENTRY")
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.PARTY_PROXY))
                )
                .typeResolver(new TypeResolver() {
                    @Override
                    public GraphQLObjectType getType(Object o) {
                        return rmObjectType.objectType(I_RmObjectQLRegistry.ENTRY);
                    }
                })
                .build();
        return graphQLInterfaceType;
    }

    @Override
    public String id() {
        return id;
    }
}
