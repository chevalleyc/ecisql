/*
 * Copyright (c) 2015 Christian Chevalley
 * This file is part of Project Ethercis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ethercis.graphql.datatypes.datavalue;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLObjectType;

import static graphql.Scalars.GraphQLBoolean;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

public class DvBooleanQLType extends EcisQLType {

    public static final String VALUE = "value";
    private final String id = I_RmObjectQLRegistry.DV_BOOLEAN;

    protected DvBooleanQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType graphQLObjectType = newObject()
                .name(id)
                .description("boolean data")
                .field(
                        newFieldDefinition()
                                .name(VALUE)
                                .description("Boolean value of this item")
//                                .dataFetcher(value)
                                .type(GraphQLBoolean)
                                .build()

                )
                .build();
        return graphQLObjectType;
    }

    @Override
    public String id() {
        return id;
    }
}
