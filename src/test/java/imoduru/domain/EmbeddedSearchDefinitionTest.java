package imoduru.domain;

import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;
import org.eclipse.collections.impl.factory.Maps;
import org.junit.Test;

public class EmbeddedSearchDefinitionTest {

    @Mocked
    private SearchResultCollector collector;
    @Mocked
    private SearchDefinition searchDefinition;

    @Test
    public void 自身がもつ入力パラメータ一覧を追加した新しいコンテキストで_内部に持つ検索定義に検索を委譲する() throws Exception {
        // setup
        EmbeddedSearchDefinition embeddedSearchDefinition = new EmbeddedSearchDefinition(searchDefinition);

        InputParameters inputParameters = new InputParameters(Maps.immutable.empty());
        embeddedSearchDefinition.setInputParameters(inputParameters);

        Context originalContext = new Context();
        Context newContext = new Context();

        new Expectations(originalContext) {{
            originalContext.newContext(inputParameters); result = newContext;
        }};

        // exercise
        embeddedSearchDefinition.search(collector, originalContext);

        // verify
        new Verifications() {{
            searchDefinition.search(collector, newContext); times = 1;
        }};
    }
}