package com.ethercis.graphql.datastructure.interfaces;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.common.archetyped.Archetyped;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/14/2017.
 */
public class _ArchetypedQLType extends EcisQLType {

    private final String id = I_RmObjectQLRegistry.ARCHETYPED;

    private Archetyped archetyped;

    public _ArchetypedQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType objectType = newObject()
                .name(id)
                .description("Archetypes act as the configuration basis for the particular structures of instances defined by the reference model")
                .field(
                        newFieldDefinition()
                                .name("archetype_id")
                                .description("Globally unique archetype identifier")
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.ARCHETYPED)))
                )
                .field(
                        newFieldDefinition()
                                .name("template_id")
                                .description("Globally unique template identifier")
                                .type(new GraphQLNonNull(GraphQLString))
                )
                .field(
                        newFieldDefinition()
                                .name("rm_version")
                                .description("Version of the openEHR reference model used to create this object")
                                .type(new GraphQLNonNull(GraphQLString))
                )
//                .useDataFetcher(new _ArchetypedQLIF().interfaceType())
                .build();
        return objectType;
    }

    @Override
    public String id() {
        return id;
    }
}
