package com.ethercis.graphql.datatypes.datavalue;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/7/2017.
 */
public class DvUriQLType extends EcisQLType {

    public static final String VALUE = "value";

    protected final String id = I_RmObjectQLRegistry.DV_URI;

    public DvUriQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType objectType = newObject()
                .name(id)
                .description("A reference to an object which conforms to the Universal Resource Identifier (URI) standard")
                .field(newFieldDefinition()
                                .name(VALUE)
                                .description("Displayable rendition of the item")
                                .type(new GraphQLNonNull(GraphQLString))
                                .build()
                )
                .build();
        return objectType;
    }

    @Override
    public String id() {
        return id;
    }

}
