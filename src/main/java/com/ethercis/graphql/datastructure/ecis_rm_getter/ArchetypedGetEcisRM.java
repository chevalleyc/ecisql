package com.ethercis.graphql.datastructure.ecis_rm_getter;

import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datastructure.ArchetypedQLType;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.support.identification.ArchetypeID;
import org.openehr.rm.support.identification.TemplateID;

/**
 * Created by christian on 4/11/2017.
 */
public class ArchetypedGetEcisRM extends ArchetypedQLType {

    public ArchetypedGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher archetypeId = new DataFetcher() {
        @Override
        public ArchetypeID get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((Archetyped)dataFetchingEnvironment.getSource()).getArchetypeId();
        }
    };

    DataFetcher templateId = new DataFetcher() {
        @Override
        public TemplateID get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((Archetyped)dataFetchingEnvironment.getSource()).getTemplateId();
        }
    };

    DataFetcher rmVersion = new DataFetcher() {
        @Override
        public String get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((Archetyped)dataFetchingEnvironment.getSource()).getRmVersion();
        }
    };

    @Override
    public GraphQLObjectType objectType(){
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .useDataFetcher(ARCHETYPE_ID, archetypeId)
                .useDataFetcher(TEMPLATE_ID, templateId)
                .useDataFetcher(RM_VERSION, rmVersion)
                .getObjectType();
//        objectType = rmObjectType.registerDataFetcher(objectType, ARCHETYPE_ID, archetypeId);
//        objectType = rmObjectType.registerDataFetcher(objectType, TEMPLATE_ID, templateId);
//        objectType = rmObjectType.registerDataFetcher(objectType, RM_VERSION, rmVersion);
//        return objectType;
    }
}
