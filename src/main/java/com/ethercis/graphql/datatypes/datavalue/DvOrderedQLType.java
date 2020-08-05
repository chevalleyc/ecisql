package com.ethercis.graphql.datatypes.datavalue;

import com.ethercis.graphql.commons.EcisQLUnionType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLUnionType;
import graphql.schema.TypeResolver;
import org.openehr.rm.datatypes.basic.DataValue;
import org.openehr.rm.datatypes.basic.DvBoolean;
import org.openehr.rm.datatypes.encapsulated.DvMultimedia;
import org.openehr.rm.datatypes.quantity.DvCount;
import org.openehr.rm.datatypes.quantity.DvOrdinal;
import org.openehr.rm.datatypes.quantity.DvProportion;
import org.openehr.rm.datatypes.quantity.DvQuantity;
import org.openehr.rm.datatypes.quantity.datetime.*;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.datatypes.uri.DvURI;

import static graphql.schema.GraphQLUnionType.newUnionType;

/**
 * Created by christian on 3/9/2017.
 */
public class DvOrderedQLType extends EcisQLUnionType {

    private final String id = I_RmObjectQLRegistry.DV_ORDERED;

    public DvOrderedQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLUnionType unionType() {
        GraphQLUnionType elementType = newUnionType()
                .name(id)
                .description("The leaf variant of ITEM, to which a DATA_VALUE instance is attached")
                .possibleType(rmObjectType.objectType(I_RmObjectQLRegistry.DV_COUNT))
                .possibleType(rmObjectType.objectType(I_RmObjectQLRegistry.DV_DATE))
                .possibleType(rmObjectType.objectType(I_RmObjectQLRegistry.DV_DATE_TIME))
                .possibleType(rmObjectType.objectType(I_RmObjectQLRegistry.DV_DURATION))
                .possibleType(rmObjectType.objectType(I_RmObjectQLRegistry.DV_ORDINAL))
                .possibleType(rmObjectType.objectType(I_RmObjectQLRegistry.DV_PROPORTION))
                .possibleType(rmObjectType.objectType(I_RmObjectQLRegistry.DV_QUANTITY))
//                .possibleType(rmObjectType.objectType(I_RmObjectQLRegistry.DV_TEMPORAL))
                .possibleType(rmObjectType.objectType(I_RmObjectQLRegistry.DV_TIME))
                .typeResolver(new TypeResolver() {
                    @Override
                    public GraphQLObjectType getType(Object o) {
                        return type((DataValue) o);
                    }
                })
                .build();
        return elementType;
    }


    @Override
    public String id() {
        return id;
    }

    public GraphQLObjectType type(DataValue dataValue) {
        if (dataValue instanceof DvCount)
            return rmObjectType.objectType(I_RmObjectQLRegistry.DV_COUNT);
        else if (dataValue instanceof DvDate)
            return rmObjectType.objectType(I_RmObjectQLRegistry.DV_DATE);
        else if (dataValue instanceof DvDateTime)
            return rmObjectType.objectType(I_RmObjectQLRegistry.DV_DATE_TIME);
        else if (dataValue instanceof DvDuration)
            return rmObjectType.objectType(I_RmObjectQLRegistry.DV_DURATION);
        else if (dataValue instanceof DvOrdinal)
            return rmObjectType.objectType(I_RmObjectQLRegistry.DV_ORDINAL);
        else if (dataValue instanceof DvProportion)
            return rmObjectType.objectType(I_RmObjectQLRegistry.DV_PROPORTION);
        else if (dataValue instanceof DvQuantity)
            return rmObjectType.objectType(I_RmObjectQLRegistry.DV_QUANTITY);
//        else if (dataValue instanceof DvTemporal)
//            return rmObjectType.objectType(I_RmObjectQLRegistry.DV_TEMPORAL);
        else if (dataValue instanceof DvTime)
            return rmObjectType.objectType(I_RmObjectQLRegistry.DV_TIME);
        else
            throw new IllegalArgumentException("Unsupported object class in type resolver:" + dataValue.getClass());
    }
}
