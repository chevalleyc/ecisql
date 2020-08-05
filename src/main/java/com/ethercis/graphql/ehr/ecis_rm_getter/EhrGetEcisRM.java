package com.ethercis.graphql.ehr.ecis_rm_getter;

import com.ethercis.dao.access.interfaces.I_CompositionAccess;
import com.ethercis.dao.access.interfaces.I_DomainAccess;
import com.ethercis.dao.access.interfaces.I_EhrAccess;
import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datastructure.arguments.CompositionIdArgument;
import com.ethercis.graphql.datastructure.interfaces.Arguments;
import com.ethercis.graphql.ehr.EhrQLType;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.composition.Composition;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.ehr.EHR;
import org.openehr.rm.support.identification.HierObjectID;
import org.openehr.rm.support.identification.ObjectRef;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by christian on 4/12/2017.
 */
public class EhrGetEcisRM extends EhrQLType {

    public EhrGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher systemId = new DataFetcher<HierObjectID>() {
        @Override
        public HierObjectID get(DataFetchingEnvironment dataFetchingEnvironment) {
            I_EhrAccess ehrAccess = (I_EhrAccess) dataFetchingEnvironment.getSource();
            //retrieve the system id for this ehr;
            UUID systemUUID = ehrAccess.getSystemId();
            return new HierObjectID(systemUUID.toString());
        }
    };

    DataFetcher ehrId = new DataFetcher<HierObjectID>() {
        @Override
        public HierObjectID get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((EHR) dataFetchingEnvironment.getSource()).getEhrID();
        }
    };

    DataFetcher contributions = new DataFetcher<List<ObjectRef>>() {
        @Override
        public List<ObjectRef> get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((EHR) dataFetchingEnvironment.getSource()).getContributions();
        }
    };

    DataFetcher ehrStatus = new DataFetcher<ObjectRef>() {
        @Override
        public ObjectRef get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((EHR) dataFetchingEnvironment.getSource()).getEhrStatus();
        }
    };

    DataFetcher ehrAccess = new DataFetcher<ObjectRef>() {
        @Override
        public ObjectRef get(DataFetchingEnvironment dataFetchingEnvironment) {
            return null;
        }
    };

    DataFetcher compositions = new DataFetcher<List<Composition>>() {
        @Override
        public List<Composition> get(DataFetchingEnvironment dataFetchingEnvironment) {
            Map<String, Object> arguments = dataFetchingEnvironment.getArguments();

            List<Composition> filteredCompositions = new ArrayList<>();

            //select by composition id
            if (new Arguments(arguments).hasSetArguments() && arguments.containsKey(CompositionIdArgument.getField())) {
                I_DomainAccess domainAccess = ((I_DomainAccess) dataFetchingEnvironment.getSource());
                //perform a select with the composition id passed in argument
                List argumentValue = (List) arguments.get(CompositionIdArgument.getField());
                for (Object value : argumentValue) {
                    UUID uid = UUID.fromString(value.toString());
                    try {
                        I_CompositionAccess compositionAccess = I_CompositionAccess.retrieveInstance2(domainAccess, uid);
                        if (compositionAccess != null)
                            filteredCompositions.add(compositionAccess.getContent().get(0).getComposition());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
            return filteredCompositions;
        }
    };

    DataFetcher directory = new DataFetcher<ObjectRef>() {
        @Override
        public ObjectRef get(DataFetchingEnvironment dataFetchingEnvironment) {
            return null;
        }
    };

    DataFetcher timeCreated = new DataFetcher<DvDateTime>() {
        @Override
        public DvDateTime get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((EHR) dataFetchingEnvironment.getSource()).getTimeCreated();
        }
    };

    @Override
    public GraphQLObjectType objectType() {
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .useDataFetcher(SYSTEM_ID, systemId)
                .useDataFetcher(EHR_ID, ehrId)
                .useDataFetcher(CONTRIBUTIONS, contributions)
                .useDataFetcher(EHR_STATUS, ehrStatus)
                .useDataFetcher(EHR_ACCESS, ehrAccess)
                .useDataFetcher(COMPOSITIONS, compositions)
                .useDataFetcher(DIRECTORY, directory)
                .useDataFetcher(TIME_CREATED, timeCreated)
                .getObjectType();
    }

}
