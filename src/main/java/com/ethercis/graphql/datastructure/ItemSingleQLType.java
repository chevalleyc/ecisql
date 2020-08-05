package com.ethercis.graphql.datastructure;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.*;
import org.openehr.rm.datastructure.itemstructure.ItemSingle;
import org.openehr.rm.datastructure.itemstructure.representation.Element;

import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/9/2017.
 */
public class ItemSingleQLType extends EcisQLType {

    private final String id = I_RmObjectQLRegistry.ITEM_SINGLE;

    protected ItemSingleQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    DataFetcher elementFetcher = new DataFetcher() {
        @Override
        public Element get(DataFetchingEnvironment dataFetchingEnvironment) {
            return ((ItemSingle) dataFetchingEnvironment.getSource()).getItem();
        }
    };

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType elementType = newObject()
                .name(id)
                .description("Logical single valueFetcher data structure")
                .withInterface(rmObjectType.interfaceType(I_RmObjectQLRegistry.DATA_STRUCTURE))
                .withInterface(rmObjectType.interfaceType(I_RmObjectQLRegistry.ITEM_STRUCTURE))
                .field(
                        rmObjectType.interfaceType(I_RmObjectQLRegistry.DATA_STRUCTURE).getFieldDefinition("name")
                )
                .field(
                        rmObjectType.interfaceType(I_RmObjectQLRegistry.DATA_STRUCTURE).getFieldDefinition("archetype_node_id")
                )
//                .field(
//                        newFieldDefinition()
//                                .name("uid")
//                                .description("Runtime name of this fragment")
//                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLType.DV_TEXT)))
//                )
                .field(
                        newFieldDefinition()
                                .name("links")
                                .description("Links to other archetyped structures")
                                .type(new GraphQLList(rmObjectType.objectType(I_RmObjectQLRegistry.LINK)))
                )
                .field(
                        newFieldDefinition()
                                .name("archetype_details")
                                .description("Details of archetyping used on this node")
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.ARCHETYPED))
                )
//                .field(
//                        newFieldDefinition()
//                                .name("feeder_audit")
//                                .description("Audit trail from non-openEHR system of original commit of information forming the content of this node")
//                                .type(rmObjectType.objectType(I_RmObjectQLType.FEEDER_AUDIT))
//                )
                .field(
                        newFieldDefinition()
                                .name("item")
                                .description("Element contained by this item single")
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.ELEMENT)))
                                .dataFetcher(elementFetcher)
                )
                .build();
        return elementType;
    }

    @Override
    public String id() {
        return id;
    }
}
