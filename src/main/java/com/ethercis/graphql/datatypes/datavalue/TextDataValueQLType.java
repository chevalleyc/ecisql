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
import org.openehr.rm.datatypes.quantity.datetime.DvDate;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;
import org.openehr.rm.datatypes.quantity.datetime.DvTime;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.datatypes.uri.DvURI;

import static graphql.schema.GraphQLUnionType.newUnionType;

/**
 * Created by christian on 3/9/2017.
 */
public class TextDataValueQLType extends EcisQLUnionType {

    private final String id = I_RmObjectQLRegistry.TEXT_DATA_VALUE;

    public TextDataValueQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLUnionType unionType() {
        GraphQLUnionType elementType = newUnionType()
                .name(id)
                .description("The leaf variant of ITEM, to which a DATA_VALUE instance is attached")
                .possibleType(rmObjectType.objectType(I_RmObjectQLRegistry.DV_TEXT))
                .possibleType(rmObjectType.objectType(I_RmObjectQLRegistry.DV_CODED_TEXT))
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
        if (dataValue instanceof DvCodedText)
            return rmObjectType.objectType(I_RmObjectQLRegistry.DV_CODED_TEXT);
        else if (dataValue instanceof DvText)
            return rmObjectType.objectType(I_RmObjectQLRegistry.DV_TEXT);
        else
            throw new IllegalArgumentException("Unsupported object class in type resolver:" + dataValue.getClass());
    }
}
