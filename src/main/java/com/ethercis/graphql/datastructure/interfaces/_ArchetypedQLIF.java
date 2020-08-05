package com.ethercis.graphql.datastructure.interfaces;

import com.ethercis.graphql.commons.EcisQLInterfaceType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.*;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLInterfaceType.newInterface;

/**
 * Created by christian on 3/7/2017.
 */
public class _ArchetypedQLIF extends EcisQLInterfaceType {

    private final String id = I_RmObjectQLRegistry.ARCHETYPED;

    protected _ArchetypedQLIF(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    //TODO: support interval!
    @Override
    public GraphQLInterfaceType interfaceType() {
        GraphQLInterfaceType graphQLInterfaceType = newInterface()
                .name(id)
                .description("Archetypes act as the configuration basis for the particular structures of instances defined by the reference model")
//                .field(
//                        newFieldDefinition()
//                                .name("archetype_id")
//                                .description("Globally unique archetype identifier")
//                                .type(new GraphQLNonNull(new ArchetypeIdQLType().objectType()))
//                )
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
                .typeResolver(new TypeResolver() {
                    @Override
                    public GraphQLObjectType getType(Object o) {
//                        return new _ArchetypedQLType((Archetyped)o).objectType();
                        return null;
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
