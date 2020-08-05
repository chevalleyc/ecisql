package com.ethercis.graphql.generator;

import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.composition.Composition;

/**
 * Created by christian on 5/24/2017.
 */
public interface I_Exemplify {

    Exemplify generate(Composition composition) throws Exception;

    void processItem(Locatable locatable) throws Exception;

    String modelize();

    String modelize(Integer tabs);

    String modelize(String prefix, String suffix);

    String modelize(String prefix, String suffix, Integer tabs);
}
