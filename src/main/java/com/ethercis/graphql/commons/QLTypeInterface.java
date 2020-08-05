package com.ethercis.graphql.commons;

import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLInterfaceType;
import graphql.schema.GraphQLObjectType;
import org.openehr.build.RMObjectBuilder;

import java.util.List;

/**
 * Created by christian on 4/14/2017.
 */
public class QLTypeInterface implements I_QLTypeInterface {

    private GraphQLObjectType objectType;
    private I_RmObjectQLRegistry registry;

    public QLTypeInterface(I_RmObjectQLRegistry registry, GraphQLObjectType objectType) {
        this.objectType = objectType;
        this.registry = registry;
    }

    @Override
    public I_QLTypeInterface withInterface(GraphQLInterfaceType interfaceType) {
        List<GraphQLFieldDefinition> fieldDefinitions = objectType.getFieldDefinitions();
        fieldDefinitions.addAll(interfaceType.getFieldDefinitions());

        List<GraphQLInterfaceType> interfaceTypes = objectType.getInterfaces();
        interfaceTypes.add(interfaceType);

        GraphQLObjectType newObject = new GraphQLObjectType(objectType.getName(), objectType.getDescription(), fieldDefinitions, interfaceTypes);
        objectType = newObject;
        return this;
    }

    @Override
    public I_QLTypeInterface withInterface(String interfaceId) {
        return withInterface(registry.interfaceType(interfaceId));
    }

    @Override
    public GraphQLObjectType getObjectType(){
        return objectType;
    }

}
