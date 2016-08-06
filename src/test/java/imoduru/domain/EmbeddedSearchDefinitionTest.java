package imoduru.domain;

import imoduru.test.TestConstant;
import org.eclipse.collections.impl.factory.Lists;
import org.junit.Test;

import static imoduru.test.TestConstant.*;

public class EmbeddedSearchDefinitionTest {

    @Test
    public void name() throws Exception {
        // setup
        SearchDefinition searchDefinition = new SearchDefinition(SEARCH_DEFINITION_NAME_A);
        InputParameterDefinition inputParameterDefinition = new InputParameterDefinition(Lists.immutable.of(
            INPUT_PARAMETER_NAME_1,
            INPUT_PARAMETER_NAME_2
        ));
        searchDefinition.setInputParameterDefinition(inputParameterDefinition);
        EmbeddedSearchDefinition embeddedSearchDefinition = new EmbeddedSearchDefinition(searchDefinition);

        // exercise





    }
}