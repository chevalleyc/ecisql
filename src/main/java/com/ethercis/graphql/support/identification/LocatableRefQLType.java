package com.ethercis.graphql.support.identification;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.QLTypeInterface;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLTypeReference;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/9/2017.
 */
public class LocatableRefQLType extends EcisQLType {

    public static final String ID_NAMESPACE = "id_namespace";
    public static final String TYPE = "type";
    public static final String ID = "id";
    public static final String PATH = "path";
    private final String id = I_RmObjectQLRegistry.LOCATABLE_REF;

    protected LocatableRefQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType elementType = newObject()
                .name(id)
                .description("Purpose Reference to a LOCATABLE instance inside the top-level content structure inside a VERSION<T>")
//                .field(
//                        newFieldDefinition()
//                                .name(NAMESPACE)
//                                .description("Namespace to which this identifier belongs in the local system context")
//                                .type(new GraphQLNonNull(GraphQLString))
//
//                )
//                .field(
//                        newFieldDefinition()
//                                .name(TYPE)
//                                .description("Name of the class (concrete or abstract) of object to which this identifier type refers")
//                                .type(new GraphQLNonNull(GraphQLString))
//
//                )
                .field(
                        newFieldDefinition()
                                .name(ID)
                                .description("Globally unique id of an object, regardless of where it is stored")
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.OBJECT_ID)))

                )
//                .field(
//                        newFieldDefinition()
//                                .name(PATH)
//                                .description("The path to an instance in question, as an absolute path with respect to the object found at VERSION")
//                                .type(GraphQLString)
//
//                )
//                .field(
//                        newFieldDefinition()
//                                .name("i")
//                                .description("The path to an instance in question, as an absolute path with respect to the object found at VERSION")
//                                .type(GraphQLString)
//
//                )
                .build();

        return elementType;
    }

    @Override
    public String id() {
        return id;
    }
}
