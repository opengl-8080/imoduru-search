package imoduru.domain.search;

import imoduru.domain.search.expression.ConditionExpressions;
import imoduru.domain.search.result.SearchResult;
import imoduru.domain.search.result.TableSearchResult;
import imoduru.domain.search.sort.SortConditions;
import imoduru.domain.table.TableAlias;
import lombok.ToString;

/**
 * デフォルトの検索結果コレクター実装.
 */
@ToString
public class DefaultSearchResultCollector implements SearchResultCollector {

    private final SearchExecutor searchExecutor;
    private final SearchResult searchResult;

    public DefaultSearchResultCollector(SearchExecutor searchExecutor, SearchResult searchResult) {
        this.searchExecutor = searchExecutor;
        this.searchResult = searchResult;
    }

    @Override
    public void search(TableAlias tableAlias, ConditionExpressions conditionExpressions, SortConditions sortConditions) {
        TableSearchResult tableSearchResult = this.searchExecutor.search(tableAlias, conditionExpressions, sortConditions);
        this.searchResult.put(tableAlias, tableSearchResult);
    }
}
