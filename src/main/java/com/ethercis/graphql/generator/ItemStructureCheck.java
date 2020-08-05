package com.ethercis.graphql.generator;

import java.util.Arrays;

/**
 * Created by christian on 5/24/2017.
 */
public class ItemStructureCheck {

    private String[] itemStructureClasses = {"Element","Cluster","ItemSingle","ItemList","ItemTable","ItemTree","History","IntervalEvent","PointEvent"};

    Class attributeClass;

    public ItemStructureCheck(Class attributeClass) {
        this.attributeClass = attributeClass;
    }

    public boolean isStructural(){
        if (attributeClass == null)
            return false;
        return Arrays.asList(itemStructureClasses).contains(attributeClass.getSimpleName());
    }
}
