package com.ethercis.graphql.datastructure.interfaces;

import com.ethercis.graphql.commons.EcisQLInterfaceType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.*;
import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.datastructure.history.Event;
import org.openehr.rm.datastructure.itemstructure.ItemSingle;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLInterfaceType.newInterface;

/**
 * Created by christian on 3/13/2017.
 */
public class DataStructureQLIF extends EcisQLInterfaceType {
    public static final String NAME = "name";
    public static final String ARCHETYPE_NODE_ID = "archetype_node_id";
    public static final String LINKS = "links";
    public static final String ARCHETYPE_DETAILS = "archetype_details";
    private final String id = I_RmObjectQLRegistry.DATA_STRUCTURE;

    protected DataStructureQLIF(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    //TODO: support interval!
    @Override
    public GraphQLInterfaceType interfaceType() {
        GraphQLInterfaceType graphQLInterfaceType = newInterface()
                .name(id)
                .description("Abstract parent class of all data structure types")
                .field(
                        newFieldDefinition()
                                .name(NAME)
                                .description("Runtime name of this fragment")
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.DV_TEXT)))
//                                .dataFetcher(nameFetcher)
                )
                .field(
                        newFieldDefinition()
                                .name(ARCHETYPE_NODE_ID)
                                .description("Design-time archetype id of this node taken from its generating archetype")
                                .type(new GraphQLNonNull(GraphQLString))
//                                .dataFetcher(archetypeNodeIdFetcher)
                )
//                .field(
//                        newFieldDefinition()
//                                .name("uid")
//                                .description("Runtime name of this fragment")
//                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLType.DV_TEXT)))
//                )
                .field(
                        newFieldDefinition()
                                .name(LINKS)
                                .description("Links to other archetyped structures")
                                .type(new GraphQLList(rmObjectType.objectType(I_RmObjectQLRegistry.LINK)))
                )
                .field(
                        newFieldDefinition()
                                .name(ARCHETYPE_DETAILS)
                                .description("Details of archetyping used on this node")
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.ARCHETYPED))
                )
//                .field(
//                        newFieldDefinition()
//                                .name("feeder_audit")
//                                .description("Audit trail from non-openEHR system of original commit of information forming the content of this node")
//                                .type(rmObjectType.objectType(I_RmObjectQLType.FEEDER_AUDIT))
//                )
                .typeResolver(new TypeResolver() {
                    @Override
                    public GraphQLObjectType getType(Object source) {
                        if (source instanceof Event)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.EVENT);
                        else
                            throw new IllegalArgumentException("Unhandled item class:" + source.getClass());
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
