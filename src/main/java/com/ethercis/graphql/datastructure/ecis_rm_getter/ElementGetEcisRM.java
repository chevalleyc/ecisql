package com.ethercis.graphql.datastructure.ecis_rm_getter;

import com.ethercis.ehr.encode.wrappers.element.ElementWrapper;
import com.ethercis.graphql.commons.QLObjectEcisFetch;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datastructure.ElementQLType;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datatypes.basic.DataValue;
import org.openehr.rm.datatypes.text.DvCodedText;

/**
 * Created by christian on 4/12/2017.
 */
public class ElementGetEcisRM extends ElementQLType {

    public ElementGetEcisRM(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher valueFetcher = new DataFetcher() {
        @Override
        public DataValue get(DataFetchingEnvironment dataFetchingEnvironment) {
            DataValue returnDataValue = null;
            Object elementSource = dataFetchingEnvironment.getSource();
            if (elementSource instanceof Element)
                returnDataValue = ((Element) elementSource).getValue();
            else if (elementSource instanceof ElementWrapper) {
                if (isSetValuesOnly() && !((ElementWrapper) elementSource).dirtyBitSet())
                    returnDataValue = null;
                else {
                    Element element = ((ElementWrapper) elementSource).getAdaptedElement();
                    returnDataValue = element.getValue();
                }
            } else
                throw new IllegalArgumentException("Unhandled Element type:" + elementSource.toString());

            return returnDataValue;
        }
    };

    DataFetcher nullFlavourFetcher = new DataFetcher() {
        @Override
        public DvCodedText get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((Element) dataFetchingEnvironment.getSource()).getNullFlavour();
        }
    };

    @Override
    public GraphQLObjectType objectType() {
        return new QLObjectEcisFetch(rmObjectType, super.objectType())
                .useDataFetcher(VALUE, valueFetcher)
                .useDataFetcher(NULL_FLAVOUR, nullFlavourFetcher)
                .getObjectType();
    }


}
