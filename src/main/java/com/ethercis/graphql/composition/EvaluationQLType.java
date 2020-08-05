package com.ethercis.graphql.composition;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.QLTypeInterface;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.*;

import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/9/2017.
 */
public class EvaluationQLType extends EcisQLType {

    public static final String DATA = "data";
    private final String id = I_RmObjectQLRegistry.EVALUATION;

    public EvaluationQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType elementType = newObject()
                .name(id)
                .description("Entry type for evaluation statements")
                .field(
                        newFieldDefinition()
                                .name(DATA)
                                .description("The data of this evaluation, in the form of a spatial data structure")
                                .type(new GraphQLNonNull(rmObjectType.unionType(I_RmObjectQLRegistry.ITEM_STRUCTURE)))
//                                .dataFetcher(dataFetcher)
                )
                .build();
        elementType = new QLTypeInterface(rmObjectType, elementType)
                .withInterface(I_RmObjectQLRegistry.CONTENT_ITEM)
                .withInterface(I_RmObjectQLRegistry.CARE_ENTRY)
                .getObjectType();

        return elementType;
    }

    @Override
    public String id() {
        return id;
    }
}
