package com.ethercis.graphql.datastructure;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLObjectType;

import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/9/2017.
 */
public class PartyProxyQLType extends EcisQLType {

    public static final String EXTERNAL_REF = "external_ref";
    private final String id = I_RmObjectQLRegistry.PARTY_PROXY;

    protected PartyProxyQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType objectType = newObject()
                .name(id)
                .description("Abstract concept of a proxy description of a party")
                .field(
                        newFieldDefinition()
                                .name(EXTERNAL_REF)
                                .description("Optional reference to more detailed demographic or identification information for this party, in an external system")
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.PARTY_REF))
                )
                .build();

        return objectType;
    }

    @Override
    public String id() {
        return id;
    }
}
