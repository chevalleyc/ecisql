package com.ethercis.graphql.datastructure;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.QLTypeInterface;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;

import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/9/2017.
 */
public class HistoryQLType extends EcisQLType {

    public static final String ORIGIN = "origin";
    public static final String PERIOD = "period";
    public static final String DURATION = "duration";
    public static final String SUMMARY = "summary";
    public static final String EVENTS = "events";

    private final String id = I_RmObjectQLRegistry.HISTORY;

    protected HistoryQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType elementType = newObject()
                .name(id)
                .description("Root object of a linear history, i.e. time series structure")
                .field(
                        newFieldDefinition()
                                .name(ORIGIN)
                                .description("Time origin of this event history")
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.DV_DATE_TIME)))

                )
                .field(
                        newFieldDefinition()
                                .name(PERIOD)
                                .description("Period between samples in this segment if periodic")
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.DV_DURATION))

                )
                .field(
                        newFieldDefinition()
                                .name(DURATION)
                                .description("Duration of the entire History")
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.DV_DURATION))

                )
                .field(
                        newFieldDefinition()
                                .name(SUMMARY)
                                .description("Optional summary data that aggregates, organizes, reduces and transforms the event series")
                                .type(rmObjectType.unionType(I_RmObjectQLRegistry.ITEM_STRUCTURE))

                )
                .field(
                        newFieldDefinition()
                                .name(EVENTS)
                                .description("The events in the series")
                                .type(new GraphQLList(rmObjectType.objectType(I_RmObjectQLRegistry.EVENT)))

                )
                .build();
        elementType = new QLTypeInterface(rmObjectType, elementType)
                .withInterface(I_RmObjectQLRegistry.DATA_STRUCTURE)
                .getObjectType();

        return elementType;
    }

    @Override
    public String id() {
        return id;
    }
}
