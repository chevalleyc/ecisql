package com.ethercis.graphql.datatypes.datavalue;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.*;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/7/2017.
 */
public class DvIdentifierQLType extends EcisQLType {

    public static final String ISSUER = "issuer";
    public static final String ASSIGNER = "assigner";
    public static final String ID = "id";
    public static final String TYPE = "type";
    private final String id = I_RmObjectQLRegistry.DV_IDENTIFIER;

    public DvIdentifierQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType graphQLObjectType = newObject()
                .name(id)
                .description("Type for representing identifiers of real-world entities")
                .field(
                        newFieldDefinition()
                                .name(ISSUER)
                                .description("Optional authority which issues the kind of id used in the id field of this object")
//                                .dataFetcher(issuer)
                                .type(GraphQLString)
                                .build()
                )
                .field(
                        newFieldDefinition()
                                .name(ASSIGNER)
                                .description("Optional organisation that assigned the id to the item being identified")
//                                .dataFetcher(assigner)
                                .type(GraphQLString)
                                .build()
                )
                .field(
                        newFieldDefinition()
                                .name(ID)
                                .description("The identifier value")
//                                .dataFetcher(identifer)
                                .type(new GraphQLNonNull(GraphQLString))
                                .build()
                )
                .field(
                        newFieldDefinition()
                                .name(TYPE)
                                .description("Optional identifier type, such as prescription , or Social Security Number")
//                                .dataFetcher(type)
                                .type(GraphQLString)
                                .build()
                )
                .build();
        return graphQLObjectType;
    }

    @Override
    public String id() {
        return id;
    }
}
