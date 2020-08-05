package com.ethercis.graphql.datatypes.datavalue;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.QLTypeInterface;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLObjectType;

import static graphql.Scalars.*;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/7/2017.
 */
public class DvMultimediaQLType extends EcisQLType {

    public static final String MEDIA_TYPE = "media_type";
    private final String id = I_RmObjectQLRegistry.DV_MULTIMEDIA;

    public DvMultimediaQLType(I_RmObjectQLRegistry rmObjectQLType) {
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
                                .name(MEDIA_TYPE)
                                .description("Data media type coded from openEHR code set media types")
//                                .dataFetcher(magnitudeFetcher)
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.CODE_PHRASE))
                )
                .build();


        return objectType;
    }

    @Override
    public String id() {
        return null;
    }
}
