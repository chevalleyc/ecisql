package com.ethercis.graphql.datatypes.datavalue;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.QLTypeInterface;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;

import static graphql.Scalars.*;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/7/2017.
 */
public class DvProportionQLType extends EcisQLType {

    public static final String NUMERATOR = "numerator";
    public static final String DENOMINATOR = "denominator";
    public static final String TYPE = "type";
    public static final String PRECISION = "precision";
    private final String id = I_RmObjectQLRegistry.DV_PROPORTION;

    public DvProportionQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType objectType = newObject()
                .name(id)
                .description("Models a ratio of values")
                .field(
                        newFieldDefinition()
                                .name("pk_ratio")
                                .description("Ratio type")
                                .type(GraphQLInt)
                )
                .field(
                        newFieldDefinition()
                                .name("pk_unitary")
                                .description("Denominator must be 1")
                                .type(GraphQLInt)
                )
                .field(
                        newFieldDefinition()
                                .name("pk_percent")
                                .description("Denominator is 100")
                                .type(GraphQLInt)
                )
                .field(
                        newFieldDefinition()
                                .name("pk_fraction")
                                .description("Numerator and denominator are integral")
                                .type(GraphQLInt)
                )
                .field(
                        newFieldDefinition()
                                .name("pk_integer_fraction")
                                .description("Numerator and denominator are integral")
                                .type(GraphQLInt)
                )
                .field(
                        newFieldDefinition()
                                .name(NUMERATOR)
                                .description("Numerator of ratio")
//                                .dataFetcher(numerator)
                                .type(new GraphQLNonNull(GraphQLFloat))
                )
                .field(
                        newFieldDefinition()
                                .name(DENOMINATOR)
                                .description("Denominator of ratio")
//                                .dataFetcher(denominator)
                                .type(new GraphQLNonNull(GraphQLFloat))
                )
                .field(
                        newFieldDefinition()
                                .name(TYPE)
                                .description("Indicates semantic type of proportion")
//                                .dataFetcher(type)
                                .type(new GraphQLNonNull(GraphQLInt))
                )
                .field(
                        newFieldDefinition()
                                .name(PRECISION)
                                .description("Precision to which the value of the quantity is expressed")
//                                .dataFetcher(precision)
                                .type(GraphQLInt)
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
        return id;
    }
}
