package imoduru.domain.search;

import imoduru.domain.search.expression.ConditionExpressions;
import imoduru.domain.search.result.SearchResult;
import imoduru.domain.search.result.TableSearchResult;
import imoduru.domain.search.sort.SortConditions;
import imoduru.domain.table.TableAlias;
import mockit.Mocked;
import mockit.Verifications;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class DefaultSearchResultCollectorTest {

    @Mocked
    private SearchExecutor searchExecutor;
    @Mocked
    private TableSearchResult tableSearchResult;
    @Mocked
    private TableAlias tableAlias;
    @Mocked
    private ConditionExpressions expressions;
    @Mocked
    private SortConditions sortConditions;

    private SearchResult searchResult;
    private DefaultSearchResultCollector collector;

    @Before
    public void setUp() throws Exception {
        searchResult = new SearchResult();
        collector = new DefaultSearchResultCollector(searchExecutor, searchResult);
    }

    @Test
    public void 引数で渡した条件で検索の実行を委譲する() throws Exception {
        // exercise
        collector.search(tableAlias, expressions, sortConditions);

        // verify
        new Verifications() {{
            searchExecutor.search(tableAlias, expressions, sortConditions); times = 1;
        }};
    }

    @Test
    public void 検索結果が保存される() throws Exception {
        // exercise
        collector.search(tableAlias, expressions, sortConditions);

        // verify
        SearchResult expected = new SearchResult();
        expected.put(tableAlias, tableSearchResult);

        assertThat(searchResult.hasSameValue(expected)).isTrue();
    }
}