package com.ethercis.graphql.datatypes.interfaces;

import com.ethercis.graphql.commons.EcisQLInterfaceType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.*;
import org.openehr.rm.datatypes.quantity.datetime.DvDate;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;
import org.openehr.rm.datatypes.quantity.datetime.DvTime;

import static graphql.Scalars.GraphQLFloat;
import static graphql.Scalars.GraphQLInt;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLInterfaceType.newInterface;

/**
 * Created by christian on 3/9/2017.
 */
public class DvDateTimeStaticsQLIF extends EcisQLInterfaceType {
    private final String id = I_RmObjectQLRegistry.DATE_TIME_STATICS;

    public DvDateTimeStaticsQLIF(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    //TODO: support interval!
    @Override
    public GraphQLInterfaceType interfaceType() {
        GraphQLInterfaceType graphQLInterfaceType = newInterface()
                .name(id)
                .description("Abstract class defining various date/time constants")
                .field(
                        newFieldDefinition()
                                .name("seconds_in_minute")
                                .description("Seconds")
                                .type(GraphQLInt)
                                .staticValue(60)
                )
                .field(
                        newFieldDefinition()
                                .name("minutes_in_hour")
                                .description("Minutes")
                                .type(GraphQLInt)
                                .staticValue(60)
                )
                .field(
                        newFieldDefinition()
                                .name("hours_in_day")
                                .description("Hours")
                                .type(GraphQLInt)
                                .staticValue(24)
                )
                .field(
                        newFieldDefinition()
                                .name("nominal_days_in_month")
                                .description("Days in month")
                                .type(GraphQLFloat)
                                .staticValue(32.42)
                )
                .field(
                        newFieldDefinition()
                                .name("max_days_in_month")
                                .description("Max days in month")
                                .type(GraphQLInt)
                                .staticValue(31)
                )
                .field(
                        newFieldDefinition()
                                .name("days_in_year")
                                .description("Days in year")
                                .type(GraphQLInt)
                                .staticValue(365)
                )
                .field(
                        newFieldDefinition()
                                .name("days_in_leap_year")
                                .description("Days in leap year")
                                .type(GraphQLInt)
                                .staticValue(366)
                )
                .field(
                        newFieldDefinition()
                                .name("max_days_in_year")
                                .description("Max days in year")
                                .type(GraphQLInt)
                                .staticValue(366)
                )
                .field(
                        newFieldDefinition()
                                .name("nominal_days_in_year")
                                .description("Nominal days in year")
                                .type(GraphQLFloat)
                                .staticValue(365.24)
                )
                .field(
                        newFieldDefinition()
                                .name("days_in_week")
                                .description("Days in week")
                                .type(GraphQLInt)
                                .staticValue(7)
                )
                .field(
                        newFieldDefinition()
                                .name("months_in_year")
                                .description("Months in year")
                                .type(GraphQLInt)
                                .staticValue(12)
                )
                .field(
                        newFieldDefinition()
                                .name("min_timezone_hour")
                                .description("Minimum hour value of a timezone")
                                .type(GraphQLInt)
                                .staticValue(12)
                )
                .field(
                        newFieldDefinition()
                                .name("max_timezone_hour")
                                .description("Maximum hour value of a timezone")
                                .type(GraphQLInt)
                                .staticValue(13)
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
                            throw new IllegalArgumentException("Unhandled type:" + source.getClass());
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
