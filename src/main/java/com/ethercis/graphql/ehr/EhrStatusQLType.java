package com.ethercis.graphql.ehr;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.QLTypeInterface;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/9/2017.
 */
public class EhrStatusQLType extends EcisQLType {

    public static final String SUBJECT = "subject";
    public static final String OTHER_DETAILS = "other_details";
    private final String id = I_RmObjectQLRegistry.EHR_STATUS;

    protected EhrStatusQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType elementType = newObject()
                .name(id)
                .description("Single object per EHR containing various EHR-wide status flags and settings")
                .field(
                        newFieldDefinition()
                                .name(SUBJECT)
                                .description("The subject of this EHR")
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.PARTY_REF)))
//                                .dataFetcher(dataFetcher)
                )
                .field(
                        newFieldDefinition()
                                .name(OTHER_DETAILS)
                                .description("Design-time archetype id of this node taken from its generating archetype")
                                .type(rmObjectType.unionType(I_RmObjectQLRegistry.ITEM_STRUCTURE))
//                                .dataFetcher(dataFetcher)
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
