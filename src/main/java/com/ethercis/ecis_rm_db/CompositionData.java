package com.ethercis.ecis_rm_db;

import com.ethercis.dao.access.interfaces.I_CompositionAccess;
import com.ethercis.dao.access.interfaces.I_DomainAccess;
import com.ethercis.dao.access.interfaces.I_EntryAccess;

import java.util.UUID;

/**
 * Created by christian on 6/6/2017.
 */
public class CompositionData {

    I_DomainAccess domainAccess;
    UUID compositionId;

    public CompositionData(I_DomainAccess domainAccess, UUID compositionId) {
        this.domainAccess = domainAccess;
        this.compositionId = compositionId;
    }

    public String templateId() throws Exception {
        I_CompositionAccess compositionAccess = I_CompositionAccess.retrieveInstance(domainAccess, compositionId);

        if (compositionAccess != null){
            //retrieve the corresponding entry
            I_EntryAccess entryAccess = compositionAccess.getContent().get(0);
            if (entryAccess != null)
                return entryAccess.getTemplateId();
        }
        return null;
    }
}
