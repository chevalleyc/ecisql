package com.ethercis.graphql.datatypes.interfaces;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLObjectType;

import static graphql.Scalars.GraphQLFloat;
import static graphql.Scalars.GraphQLInt;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/14/2017.
 */
public class DvDateTimeStaticsQLType extends EcisQLType {

    public static final String SECONDS_IN_MINUTE = "seconds_in_minute";
    public static final String MINUTES_IN_HOUR = "minutes_in_hour";
    public static final String HOURS_IN_DAY = "hours_in_day";
    public static final String NOMINAL_DAYS_IN_MONTH = "nominal_days_in_month";
    public static final String MAX_DAYS_IN_MONTH = "max_days_in_month";
    public static final String DAYS_IN_YEAR = "days_in_year";
    public static final String DAYS_IN_LEAP_YEAR = "days_in_leap_year";
    public static final String MAX_DAYS_IN_YEAR = "max_days_in_year";
    public static final String NOMINAL_DAYS_IN_YEAR = "nominal_days_in_year";
    public static final String DAYS_IN_WEEK = "days_in_week";
    public static final String MONTHS_IN_YEAR = "months_in_year";
    public static final String MIN_TIMEZONE_HOUR = "min_timezone_hour";
    public static final String MAX_TIMEZONE_HOUR = "max_timezone_hour";

    private final String id = I_RmObjectQLRegistry.DATE_TIME_STATICS;

    protected DvDateTimeStaticsQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType objectType = newObject()
                .name(id)
                .description("Abstract class defining various date/time constants")
                .field(
                        newFieldDefinition()
                                .name(SECONDS_IN_MINUTE)
                                .description("Seconds")
                                .type(GraphQLInt)
                                .staticValue(60)
                )
                .field(
                        newFieldDefinition()
                                .name(MINUTES_IN_HOUR)
                                .description("Minutes")
                                .type(GraphQLInt)
                                .staticValue(60)
                )
                .field(
                        newFieldDefinition()
                                .name(HOURS_IN_DAY)
                                .description("Hours")
                                .type(GraphQLInt)
                                .staticValue(24)
                )
                .field(
                        newFieldDefinition()
                                .name(NOMINAL_DAYS_IN_MONTH)
                                .description("Days in month")
                                .type(GraphQLFloat)
                                .staticValue(32.42)
                )
                .field(
                        newFieldDefinition()
                                .name(MAX_DAYS_IN_MONTH)
                                .description("Max days in month")
                                .type(GraphQLInt)
                                .staticValue(31)
                )
                .field(
                        newFieldDefinition()
                                .name(DAYS_IN_YEAR)
                                .description("Days in year")
                                .type(GraphQLInt)
                                .staticValue(365)
                )
                .field(
                        newFieldDefinition()
                                .name(DAYS_IN_LEAP_YEAR)
                                .description("Days in leap year")
                                .type(GraphQLInt)
                                .staticValue(366)
                )
                .field(
                        newFieldDefinition()
                                .name(MAX_DAYS_IN_YEAR)
                                .description("Max days in year")
                                .type(GraphQLInt)
                                .staticValue(366)
                )
                .field(
                        newFieldDefinition()
                                .name(NOMINAL_DAYS_IN_YEAR)
                                .description("Nominal days in year")
                                .type(GraphQLFloat)
                                .staticValue(365.24)
                )
                .field(
                        newFieldDefinition()
                                .name(DAYS_IN_WEEK)
                                .description("Days in week")
                                .type(GraphQLInt)
                                .staticValue(7)
                )
                .field(
                        newFieldDefinition()
                                .name(MONTHS_IN_YEAR)
                                .description("Months in year")
                                .type(GraphQLInt)
                                .staticValue(12)
                )
                .field(
                        newFieldDefinition()
                                .name(MIN_TIMEZONE_HOUR)
                                .description("Minimum hour value of a timezone")
                                .type(GraphQLInt)
                                .staticValue(12)
                )
                .field(
                        newFieldDefinition()
                                .name(MAX_TIMEZONE_HOUR)
                                .description("Maximum hour value of a timezone")
                                .type(GraphQLInt)
                                .staticValue(13)
                )
//                .useDataFetcher(new DvDateTimeStaticsQLIF().interfaceType())
                .build();
        return objectType;
    }

    @Override
    public String id() {
        return id;
    }
}
