package com.ethercis.graphql.datatypes.interfaces;

import com.ethercis.graphql.commons.EcisQLInterfaceType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.*;
import org.openehr.rm.datatypes.quantity.datetime.*;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLInterfaceType.newInterface;

/**
 * Created by christian on 3/7/2017.
 */
public class DvTemporalQLIF extends EcisQLInterfaceType {

    public static final String NORMAL_STATUS = "normal_status";
    public static final String MAGNITUDE_STATUS = "magnitude_status";
    private final String id = I_RmObjectQLRegistry.DV_TEMPORAL;

    protected DvTemporalQLIF(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    //TODO: support interval!
    @Override
    public GraphQLInterfaceType interfaceType() {
        GraphQLInterfaceType graphQLInterfaceType = newInterface()
                .name(id)
                .description("Abstract class defining the concept of true quantified values")
                .field(
                        newFieldDefinition()
                                .name(NORMAL_STATUS)
                                .description("Optional normal status indicator of value with respect to normal range for this value")
//                                .dataFetcher(normalStatus)
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.CODE_PHRASE))
                )
                        //TODO: normal range: DvInterval
                        //TODO: reference range: DvInterval
                .field(
                        newFieldDefinition()
                                .name(MAGNITUDE_STATUS)
                                .description("Optional status of magnitude")
//                                .dataFetcher(magnitudeStatus)
                                .type(GraphQLString)
                )
                .typeResolver(new TypeResolver() {
                    @Override
                    public GraphQLObjectType getType(Object source) {
                        if (source instanceof DvDateTime)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.DV_DATE_TIME);
                        if (source instanceof DvDate)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.DV_DATE);
                        if (source instanceof DvTime)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.DV_TIME);
                        if (source instanceof DvDuration)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.DV_DURATION);
                        else
                            throw new IllegalArgumentException("Unhandled type:"+source.getClass());

                    }
                })
                        //TODO: accuracy: DvAny
                .build();
        return graphQLInterfaceType;
    }

    @Override
    public String id() {
        return id;
    }
}
