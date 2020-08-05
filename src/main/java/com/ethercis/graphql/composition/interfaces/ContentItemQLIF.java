package com.ethercis.graphql.composition.interfaces;

import com.ethercis.graphql.commons.EcisQLInterfaceType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.*;
import org.openehr.rm.composition.content.entry.*;
import org.openehr.rm.composition.content.navigation.Section;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLInterfaceType.newInterface;

/**
 * Created by christian on 3/13/2017.
 */
public class ContentItemQLIF extends EcisQLInterfaceType {
    public static final String NAME = "name";
    public static final String ARCHETYPE_NODE_ID = "archetype_node_id";
    public static final String UID = "uid";
    public static final String LINKS = "links";
    public static final String ARCHETYPE_DETAILS = "archetype_details";
    private final String id = I_RmObjectQLRegistry.CONTENT_ITEM;

    protected ContentItemQLIF(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    //TODO: support interval!
    @Override
    public GraphQLInterfaceType interfaceType() {
        GraphQLInterfaceType graphQLInterfaceType = newInterface()
                .name(id)
                .description("Abstract ancestor of all concrete content types")
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
                .typeResolver(new TypeResolver() {
                    @Override
                    public GraphQLObjectType getType(Object source) {
                        if (source instanceof Action)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.ACTION);
                        else if (source instanceof Activity)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.ACTIVITY);
                        else if (source instanceof AdminEntry)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.ADMIN_ENTRY);
                        else if (source instanceof Evaluation)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.EVALUATION);
                        else if (source instanceof Instruction)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.INSTRUCTION);
                        else if (source instanceof Evaluation)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.INSTRUCTION_DETAILS);
                        else if (source instanceof ISMTransition)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.ISM_TRANSITION);
                        else if (source instanceof Observation)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.OBSERVATION);
                        else if (source instanceof Section)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.SECTION);
                        else
                            throw new IllegalArgumentException("Unhandled type:" + source.getClass());
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
