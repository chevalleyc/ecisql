package com.ethercis.graphql.datatypes.datavalue.ecis_rm_getter;

import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datatypes.datavalue.DvMultimediaQLType;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.datatypes.encapsulated.DvMultimedia;
import org.openehr.rm.datatypes.text.CodePhrase;

/**
 * Created by christian on 4/11/2017.
 */
public class DvMultiMediaGetEcisRM extends DvMultimediaQLType {

    public DvMultiMediaGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher mediaType = new DataFetcher() {
        @Override
        public CodePhrase get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((DvMultimedia) dataFetchingEnvironment.getSource()).getMediaType();
        }
    };


    @Override
    public GraphQLObjectType objectType() {
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .useDataFetcher(MEDIA_TYPE, mediaType)
                .getObjectType();
    }
}
