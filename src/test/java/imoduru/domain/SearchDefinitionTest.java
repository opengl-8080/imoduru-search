package imoduru.domain;

import mockit.Mocked;
import mockit.Verifications;
import org.eclipse.collections.impl.factory.Lists;
import org.junit.Test;

public class SearchDefinitionTest {

    @Mocked
    private SearchResultCollector collector;
    @Mocked
    private SearchDefinitionDetail searchDefinitionDetail;

    @Test
    public void 自身が持つ各検索定義明細に_検索の指示を出す() throws Exception {
        // setup
        SearchDefinition searchDefinition = new SearchDefinition(new SearchDefinitionName("foo"));

        searchDefinition.setSearchDefinitionDetailList(Lists.immutable.of(
            searchDefinitionDetail,
            searchDefinitionDetail,
            searchDefinitionDetail
        ));

        InputData inputData = new InputData();

        // exercise
        searchDefinition.search(collector, inputData);

        // verify
        new Verifications() {{
            searchDefinitionDetail.search(collector, inputData); times = 3;
        }};
    }
}