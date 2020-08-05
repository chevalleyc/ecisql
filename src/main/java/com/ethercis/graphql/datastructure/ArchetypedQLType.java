package com.ethercis.graphql.datastructure;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/9/2017.
 */
public class ArchetypedQLType extends EcisQLType {

    public static final String ARCHETYPE_ID = "archetype_id";
    public static final String TEMPLATE_ID = "template_id";
    public static final String RM_VERSION = "rm_version";
    private final String id = I_RmObjectQLRegistry.ARCHETYPED;

    protected ArchetypedQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

//    DataFetcher archetypeId = new DataFetcher() {
//        @Override
//        public ArchetypeID get(DataFetchingEnvironment dataFetchingEnvironment) {
//            return ((Archetyped)dataFetchingEnvironment.getSource()).getArchetypeId();
//        }
//    };
//
//    DataFetcher templateId = new DataFetcher() {
//        @Override
//        public TemplateID get(DataFetchingEnvironment dataFetchingEnvironment) {
//            return ((Archetyped)dataFetchingEnvironment.getSource()).getTemplateId();
//        }
//    };

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType elementType = newObject()
                .name(id)
                .description("Archetypes act as the configuration basis for the particular structures of instances defined by the reference model")
                .field(
                        newFieldDefinition()
                                .name(ARCHETYPE_ID)
                                .description("Globally unique archetype identifier.")
//                                .dataFetcher(archetypeId)
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.ARCHETYPE_ID)))
                )
                .field(
                        newFieldDefinition()
                                .name(TEMPLATE_ID)
                                .description("Globally unique template identifier.")
//                                .dataFetcher(templateId)
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.TEMPLATE_ID))
                )
                .field(
                        newFieldDefinition()
                                .name(RM_VERSION)
                                .description("Version of the openEHR reference model used to create this object")
//                                .dataFetcher(templateId)
                                .type(new GraphQLNonNull(GraphQLString))
                )
                .build();
        return elementType;
    }

    @Override
    public String id() {
        return id;
    }
}
