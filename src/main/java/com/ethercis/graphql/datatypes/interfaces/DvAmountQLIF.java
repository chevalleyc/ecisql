package com.ethercis.graphql.datatypes.interfaces;

import com.ethercis.graphql.commons.EcisQLInterfaceType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.*;
import org.openehr.rm.datatypes.quantity.*;
import org.openehr.rm.datatypes.quantity.datetime.DvDate;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;
import org.openehr.rm.datatypes.quantity.datetime.DvTime;

import static graphql.Scalars.GraphQLBoolean;
import static graphql.Scalars.GraphQLFloat;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLInterfaceType.newInterface;

/**
 * Created by christian on 3/7/2017.
 */
public class DvAmountQLIF extends EcisQLInterfaceType {

    public static final String ACCURACY_PERCENT = "accuracy_percent";
    public static final String ACCURACY = "accuracy";
    private final String id = I_RmObjectQLRegistry.DV_AMOUNT;

    protected DvAmountQLIF(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    //TODO: support interval!
    @Override
    public GraphQLInterfaceType interfaceType() {
        GraphQLInterfaceType  graphQLInterfaceType = newInterface()
                .name(id)
                .description("Abstract class defining the concept of relative quantified amounts")
                .field(
                        newFieldDefinition()
                                .name(ACCURACY_PERCENT)
                                .description("If True, indicates that when this objectType was created, accuracy was recorded as a percent value")
                                .type(GraphQLBoolean)
//                                .dataFetcher(accuracyIsPercent)
                                .build()
                )
                .field(
                        newFieldDefinition()
                                .name(ACCURACY)
                                .description("Accuracy of measurement")
                                .type(GraphQLFloat)
//                                .dataFetcher(accuracy)
                                .build()
                )
                .typeResolver(new TypeResolver() {
                    @Override
                    public GraphQLObjectType getType(Object source) {
                        if (source instanceof DvDate)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.DV_DATE);
                        else if (source instanceof DvDateTime)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.DV_DATE_TIME);
                        else if (source instanceof DvDuration)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.DV_DURATION);
                        else if (source instanceof DvTime)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.DV_TIME);
                        else if (source instanceof DvCount)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.DV_COUNT);
                        else if (source instanceof DvInterval)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.DV_INTERVAL);
                        else if (source instanceof DvOrdinal)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.DV_ORDINAL);
                        else if (source instanceof DvProportion)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.DV_PROPORTION);
                        else if (source instanceof DvQuantity)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.DV_QUANTITY);
                        else
                            throw new IllegalArgumentException("Unhandled type:"+source.getClass());


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
