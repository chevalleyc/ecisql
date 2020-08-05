package com.ethercis.graphql.composition.ecis_rm_getter;

import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.composition.EvaluationQLType;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.composition.content.entry.Evaluation;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;

/**
 * Created by christian on 4/12/2017.
 */
public class EvaluationGetEcisRM extends EvaluationQLType {

    public EvaluationGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher dataFetcher = new DataFetcher() {
        @Override
        public ItemStructure get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((Evaluation) dataFetchingEnvironment.getSource()).getData();
        }
    };

    @Override
    public GraphQLObjectType objectType() {
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .useDataFetcher(DATA, dataFetcher)
                .getObjectType();
    }


}
