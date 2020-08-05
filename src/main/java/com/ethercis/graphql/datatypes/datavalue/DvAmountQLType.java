package com.ethercis.graphql.datatypes.datavalue;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLObjectType;

import static graphql.Scalars.GraphQLBoolean;
import static graphql.Scalars.GraphQLFloat;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/14/2017.
 */
public class DvAmountQLType extends EcisQLType {

    public static final String ACCURACY_PERCENT = "accuracy_percent";
    public static final String ACCURACY = "accuracy";
    private final String id = I_RmObjectQLRegistry.DV_QUANTIFIED;

    protected DvAmountQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType objectType = newObject()
                .name(id)
                .description("Abstract class defining the concept of relative quantified amounts")
                .field(
                        newFieldDefinition()
                                .name(ACCURACY_PERCENT)
                                .description("If True, indicates that when this objectType was created, accuracy was recorded as a percent value")
                                .type(GraphQLBoolean)
                                .build()
                )
                .field(
                        newFieldDefinition()
                                .name(ACCURACY)
                                .description("Accuracy of measurement")
                                .type(GraphQLFloat)
                                .build()
                )
                .withInterface(rmObjectType.interfaceType(I_RmObjectQLRegistry.DV_AMOUNT))
                .build();
        return objectType;
    }

    @Override
    public String id() {
        return id;
    }
}
