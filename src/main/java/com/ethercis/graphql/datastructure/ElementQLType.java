package com.ethercis.graphql.datastructure;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.QLTypeInterface;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLObjectType;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.newObjectType;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/9/2017.
 */
public class ElementQLType extends EcisQLType {

    public static final String VALUE = "value";
    public static final String NULL_FLAVOUR = "null_flavour";
    private final String id = I_RmObjectQLRegistry.ELEMENT;

    protected ElementQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }


    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType elementType = newObject()
                .name(id)
                .description("The leaf variant of ITEM, to which a DATA_VALUE instance is attached")
                .field(
                        newFieldDefinition()
                                .name(VALUE)
                                .description("Property representing leaf value object of ELEMENT")
                                .argument(rmObjectType.argument(I_RmObjectQLRegistry.ARCHETYPE_NODE_ID_ARGUMENT))
                                .argument(rmObjectType.argument(I_RmObjectQLRegistry.NODE_NAME_ARGUMENT))
                                .type(rmObjectType.unionType(I_RmObjectQLRegistry.DATA_VALUE))
                                .build()
                )
                .field(
                        newFieldDefinition()
                                .name(NULL_FLAVOUR)
                                .description("Flavour of null value")
//                                .dataFetcher(nullFlavorFetcher)
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.DV_CODED_TEXT))
                                .build()
                )
                .build();

        elementType = new QLTypeInterface(rmObjectType, elementType).withInterface(I_RmObjectQLRegistry.ITEM).getObjectType();

        return elementType;
    }

    @Override
    public String id() {
        return id;
    }
}
