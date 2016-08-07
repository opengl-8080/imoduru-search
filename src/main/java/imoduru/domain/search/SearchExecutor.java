package imoduru.domain.search;

import imoduru.domain.search.expression.ConditionExpressions;
import imoduru.domain.search.result.TableSearchResult;
import imoduru.domain.search.sort.SortConditions;
import imoduru.domain.table.TableAlias;

/**
 * 検索を実行する.
 */
public interface SearchExecutor {

    /**
     * 検索を実行する.
     * @param table テーブル
     * @param conditionExpressions 検索条件
     * @param sortConditions ソート条件
     * @return 検索結果
     */
    TableSearchResult search(TableAlias table, ConditionExpressions conditionExpressions, SortConditions sortConditions);
}
