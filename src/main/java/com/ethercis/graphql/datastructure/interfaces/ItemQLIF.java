package com.ethercis.graphql.datastructure.interfaces;

import com.ethercis.graphql.commons.EcisQLInterfaceType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.*;
import org.openehr.rm.datastructure.itemstructure.representation.Cluster;
import org.openehr.rm.datastructure.itemstructure.representation.Element;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLInterfaceType.newInterface;

/**
 * Created by christian on 3/13/2017.
 */
public class ItemQLIF extends EcisQLInterfaceType {
    public static final String NAME = "name";
    public static final String ARCHETYPE_NODE_ID = "archetype_node_id";
    public static final String LINKS = "links";
    public static final String ARCHETYPE_DETAILS = "archetype_details";
    public static final String UID = "uid";
    private final String id = I_RmObjectQLRegistry.ITEM;

    protected ItemQLIF(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    //TODO: support interval!
    @Override
    public GraphQLInterfaceType interfaceType() {
        GraphQLInterfaceType graphQLInterfaceType = newInterface()
                .name(id)
                .description("The abstract parent of CLUSTER and ELEMENT representation classes")
                .field(
                        newFieldDefinition()
                                .name(NAME)
                                .description("Runtime name of this fragment")
                                .type(new GraphQLNonNull(rmObjectType.unionType(I_RmObjectQLRegistry.TEXT_DATA_VALUE)))
//                                .dataFetcher(name)
                )
                .field(
                        newFieldDefinition()
                                .name(ARCHETYPE_NODE_ID)
                                .description("Design-time archetype id of this node taken from its generating archetype")
                                .type(new GraphQLNonNull(GraphQLString))
//                                .dataFetcher(archetypeNodeId)
                )
                .field(
                        newFieldDefinition()
                                .name(UID)
                                .description("Optional globally unique object identifier for root points of archetyped structures")
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.UID_BASE_ID))
                )
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
                    public GraphQLObjectType getType(Object o) {
                        if (o instanceof Element)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.ELEMENT);
                        else if (o instanceof Cluster)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.CLUSTER);
                        else
                            throw new IllegalArgumentException("Unhandled item class:" + o.getClass());
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
