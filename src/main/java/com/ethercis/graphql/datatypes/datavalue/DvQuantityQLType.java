package com.ethercis.graphql.datatypes.datavalue;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.QLTypeInterface;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.*;

import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

import static graphql.Scalars.*;

/**
 * Created by christian on 3/7/2017.
 */
public class DvQuantityQLType extends EcisQLType {

    protected static final String MAGNITUDE = "magnitude";
    protected static final String PRECISION = "precision";
    protected static final String UNITS = "units";
    public static final String ACCURACY_IS_PERCENT = "accuracy_is_percent";
    public static final String ACCURACY = "accuracy";
    private final String id = I_RmObjectQLRegistry.DV_QUANTITY;

    public DvQuantityQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }


    @Override
    public GraphQLObjectType objectType() {
//        GraphQLObjectType objectType = new GraphQLObjectType(id,
//                "Quantitified type representing scientific quantities",
//                )
        GraphQLObjectType objectType = newObject()
                .name(id)
                .description("Quantitified type representing scientific quantities")
                .field(
                        newFieldDefinition()
                                .name(MAGNITUDE)
                                .description("Numeric magnitude of the quantity")
//                                .dataFetcher(magnitudeFetcher)
                                .type(GraphQLFloat)
                )
                .field(
                        newFieldDefinition()
                                .name(PRECISION)
                                .description("Precision to which the value of the quantity is expressed")
//                                .dataFetcher(precisionFetcher)
                                .type(GraphQLInt)
                )
                .field(
                        newFieldDefinition()
                                .name(UNITS)
                                .description("Stringified units UCUM syntax")
//                                .dataFetcher(unitFetcher)
                                .type(GraphQLString)
                )
                .build();

        objectType = new QLTypeInterface(rmObjectType, objectType)
                .withInterface(rmObjectType.interfaceType(I_RmObjectQLRegistry.DV_AMOUNT))
                .withInterface(rmObjectType.interfaceType(I_RmObjectQLRegistry.DV_QUANTIFIED))
                .getObjectType();

        return objectType;
    }

    @Override
    public String id() {
        return null;
    }
}
