package com.ethercis.graphql.support.identification;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/9/2017.
 */
public class PartyRefQLType extends EcisQLType {

    public static final String NAMESPACE = "namespace";
    public static final String TYPE = "type";
    public static final String ID = "id";

    private final String id = I_RmObjectQLRegistry.PARTY_REF;

    protected PartyRefQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType objectType = newObject()
                .name(id)
                .description("Identifier for parties in a demographic or identity service")
                .field(
                        newFieldDefinition()
                                .name(NAMESPACE)
                                .description("Namespace to which this identifier belongs in the local system context")
                                .type(new GraphQLNonNull(GraphQLString))
                )
                .field(
                        newFieldDefinition()
                                .name(TYPE)
                                .description("Name of the class (concrete or abstract) of object to which this identifier type refers")
                                .type(new GraphQLNonNull(GraphQLString))
                )
                .field(
                        newFieldDefinition()
                                .name(ID)
                                .description("Globally unique id of an object, regardless of where it is stored")
                                .type(new GraphQLNonNull(GraphQLString))
                )
                .build();
        return objectType;
    }

    @Override
    public String id() {
        return id;
    }
}
