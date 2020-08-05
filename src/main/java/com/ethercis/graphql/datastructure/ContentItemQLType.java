package com.ethercis.graphql.datastructure;

import com.ethercis.graphql.commons.EcisQLUnionType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.*;
import org.openehr.rm.composition.content.entry.*;
import org.openehr.rm.composition.content.navigation.Section;

import static graphql.schema.GraphQLUnionType.newUnionType;
/**
 * Created by christian on 3/13/2017.
 */
public class ContentItemQLType extends EcisQLUnionType {
    private final String id = I_RmObjectQLRegistry.CONTENT_ITEM;

    public ContentItemQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    //TODO: support interval!
    @Override
    public GraphQLUnionType unionType(){
        GraphQLUnionType unionType = newUnionType()
                .name(id)
                .description("Abstract parent class of all spatial data types")
                .possibleType(rmObjectType.objectType(I_RmObjectQLRegistry.ACTION))
//                .possibleType(rmObjectType.objectType(I_RmObjectQLType.ACTIVITY))
//                .possibleType(rmObjectType.objectType(I_RmObjectQLType.ADMIN_ENTRY))
                .possibleType(rmObjectType.objectType(I_RmObjectQLRegistry.EVALUATION))
                .possibleType(rmObjectType.objectType(I_RmObjectQLRegistry.INSTRUCTION))
                .possibleType(rmObjectType.objectType(I_RmObjectQLRegistry.OBSERVATION))
                .possibleType(rmObjectType.objectType(I_RmObjectQLRegistry.SECTION))
                .typeResolver(new TypeResolver() {
                    @Override
                    public GraphQLObjectType getType(Object object) {
                        if (object instanceof Action)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.ACTION);
                        else if (object instanceof Activity)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.ACTIVITY);
                        else if (object instanceof AdminEntry)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.ADMIN_ENTRY);
                        else if (object instanceof Evaluation)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.EVALUATION);
                        else if (object instanceof Instruction)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.INSTRUCTION);
                        else if (object instanceof Observation)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.OBSERVATION);
                        else if (object instanceof Section)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.SECTION);
                        else
                            throw new IllegalArgumentException("Unhandled type class:" + object.getClass());
                    }
                })
                .build();

        return unionType;
    }

    @Override
    public String id() {
        return id;
    }
}
