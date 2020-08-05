package com.ethercis.graphql.datastructure;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLObjectType;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/9/2017.
 */
public class PartyIdentifiedQLType extends EcisQLType {

    public static final String EXTERNAL_REF = "external_ref";
    public static final String NAME = "name";
    public static final String IDENTIFIERS = "identifiers";
    private final String id = I_RmObjectQLRegistry.PARTY_IDENTIFIED;

    protected PartyIdentifiedQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }


    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType elementType = newObject()
                .name(id)
                .description("Proxy data for an identified party other than the subject of the record")
                .field(
                        newFieldDefinition()
                                .name(EXTERNAL_REF)
                                .description("Optional reference to more detailed demographic or identification information for this party")
//                                .dataFetcher(externalRef)
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.PARTY_REF))
                                .build()
                )
                .field(
                        newFieldDefinition()
                                .name(NAME)
                                .description("Optional human-readable name")
//                                .dataFetcher(name)
                                .type(GraphQLString)
                                .build()
                )
                .field(
                        newFieldDefinition()
                                .name(IDENTIFIERS)
                                .description("One or more formal identifiers")
//                                .dataFetcher(identifiers)
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.DV_IDENTIFIER))
                                .build()
                )
                .build();
        return elementType;
    }

    @Override
    public String id() {
        return id;
    }
}
