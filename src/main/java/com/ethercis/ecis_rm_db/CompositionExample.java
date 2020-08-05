package com.ethercis.ecis_rm_db;

import com.ethercis.dao.access.interfaces.I_DomainAccess;
import com.ethercis.ehr.building.I_ContentBuilder;
import com.ethercis.graphql.generator.Exemplify;
import com.ethercis.graphql.generator.ExemplifyMode;
import org.openehr.rm.composition.Composition;

import java.util.UUID;

/**
 * Created by christian on 6/6/2017.
 */
public class CompositionExample {

    private final ExemplifyMode mode;
    Composition composition;

    public CompositionExample(Composition composition, ExemplifyMode exemplifyMode) {
        this.composition = composition;
        if (exemplifyMode == null)
            this.mode = ExemplifyMode.REQUIRED_FIELDS_ONLY;
        else
            this.mode = exemplifyMode;
    }

    public CompositionExample(I_DomainAccess domainAccess, String templateId, ExemplifyMode exemplifyMode) throws Exception {
        I_ContentBuilder contentBuilder = I_ContentBuilder.getInstance(domainAccess.getKnowledgeManager(), templateId);
        this.composition = contentBuilder.generateNewComposition();
        if (exemplifyMode == null)
            this.mode = ExemplifyMode.REQUIRED_FIELDS_ONLY;
        else
            this.mode = exemplifyMode;
    }

    public String generate() throws Exception {
        return new Exemplify(ExemplifyMode.REQUIRED_FIELDS_ONLY)
                .generate(composition)
                .modelize("query cdr {\n" +
                        "\tcomposition \n", "\n}");

    }

    public String generate(UUID compositionId) throws Exception {
        return new Exemplify(ExemplifyMode.REQUIRED_FIELDS_ONLY)
                .generate(composition)
                .modelize("query cdr {\n" +
                        "\tcomposition " + "(id: \""+compositionId+"\")",
                        "\n}");

    }
}
