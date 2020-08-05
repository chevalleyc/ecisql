package com.ethercis.graphql.cdr.ecis_rm_fetcher;

import com.ethercis.dao.access.interfaces.I_CompositionAccess;
import com.ethercis.dao.access.interfaces.I_DomainAccess;
import com.ethercis.dao.access.interfaces.I_EhrAccess;
import com.ethercis.graphql.cdr.CdrQLType;
import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datastructure.arguments.CompositionIdArgument;
import com.ethercis.graphql.datastructure.arguments.EhrIdArgument;
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
public class CdrGetEcisRM extends CdrQLType {

    public CdrGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

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

    DataFetcher ehrs = new DataFetcher<List<I_EhrAccess>>() {
        @Override
        public List<I_EhrAccess> get(DataFetchingEnvironment dataFetchingEnvironment) {
            Map<String, Object> arguments = dataFetchingEnvironment.getArguments();

            List<I_EhrAccess> filteredEhrs = new ArrayList<>();

            //select by composition id
            if (new Arguments(arguments).hasSetArguments() && arguments.containsKey(EhrIdArgument.getField())) {
                I_DomainAccess domainAccess = ((I_DomainAccess) dataFetchingEnvironment.getSource());
                //perform a select with the composition id passed in argument
                List argumentValue = (List) arguments.get(EhrIdArgument.getField());
                for (Object value : argumentValue) {
                    UUID uid = UUID.fromString(value.toString());
                    try {
                        I_EhrAccess ehrAccess = I_EhrAccess.retrieveInstance(domainAccess, uid);
                        if (ehrAccess != null)
                            filteredEhrs.add(ehrAccess);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
            return filteredEhrs;
        }
    };

    @Override
    public GraphQLObjectType objectType() {
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .useDataFetcher(COMPOSITION, compositions)
                .useDataFetcher(EHR, ehrs)
                .getObjectType();
    }

}
