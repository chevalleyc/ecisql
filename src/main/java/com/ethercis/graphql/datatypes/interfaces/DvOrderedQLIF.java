package com.ethercis.graphql.datatypes.interfaces;

import com.ethercis.graphql.commons.EcisQLInterfaceType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLInterfaceType;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLTypeReference;
import graphql.schema.TypeResolver;
import org.openehr.rm.datatypes.basic.DataValue;
import org.openehr.rm.datatypes.quantity.*;
import org.openehr.rm.datatypes.quantity.datetime.*;

import static graphql.Scalars.GraphQLBoolean;
import static graphql.Scalars.GraphQLFloat;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLInterfaceType.newInterface;

/**
 * Created by christian on 3/7/2017.
 */
public class DvOrderedQLIF extends EcisQLInterfaceType {

    public static final String NORMAL_STATUS = "normal_status";
    public static final String NORMAL_RANGE = "normal_range";
    public static final String OTHER_REFERENCE_RANGE = "other_reference_range";
    private final String id = I_RmObjectQLRegistry.DV_ORDERED;

    protected DvOrderedQLIF(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    //TODO: support interval!
    @Override
    public GraphQLInterfaceType interfaceType() {
        GraphQLInterfaceType  graphQLInterfaceType = newInterface()
                .name(id)
                .description("Abstract class defining the concept of true quantified values")
                .field(
                        newFieldDefinition()
                                .name(NORMAL_STATUS)
                                .description("Optional normal status indicator of value with respect to normal range for this value")
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.CODE_PHRASE))
//                                .dataFetcher(accuracyIsPercent)
                                .build()
                )
                .field(
                        newFieldDefinition()
                                .name(NORMAL_RANGE)
                                .description("Optional normal range")
                                .type(new GraphQLTypeReference(I_RmObjectQLRegistry.DV_INTERVAL))
//                                .dataFetcher(accuracy)
                                .build()
                )
                .field(
                        newFieldDefinition()
                                .name(OTHER_REFERENCE_RANGE)
                                .description("Optional tagged other reference ranges for this value in its particular measurement context.")
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.REFERENCE_RANGE))
//                                .dataFetcher(accuracy)
                                .build()
                )
                .typeResolver(new TypeResolver() {
                    @Override
                    public GraphQLObjectType getType(Object o) {
                        if (o instanceof DvCount)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.DV_COUNT);
                        if (o instanceof DvDate)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.DV_DATE);
                        if (o instanceof DvDateTime)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.DV_DATE_TIME);
                        if (o instanceof DvTime)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.DV_TIME);
                        if (o instanceof DvDuration)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.DV_DURATION);
                        if (o instanceof DvOrdinal)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.DV_ORDINAL);
                        if (o instanceof DvProportion)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.DV_PROPORTION);
                        if (o instanceof DvQuantity)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.DV_QUANTITY);
                        if (o instanceof DvTemporal)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.DV_TEMPORAL);
                        else
                            throw new IllegalArgumentException("Unhandled type:" + o.getClass());
                    }
                })
                .build();
        return graphQLInterfaceType;
    }

    @Override
    public String id() {
        return id;
    }
}
