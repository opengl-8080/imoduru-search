package imoduru.domain;

/**
 * 検索結果コレクター.
 */
public interface SearchResultCollector {

    /**
     * 検索を実行し、結果を収集する.
     * @param tableAlias 検索対象テーブル
     * @param conditionExpressions 検索条件
     * @param sortConditions ソート条件
     */
    void search(TableAlias tableAlias, ConditionExpressions conditionExpressions, SortConditions sortConditions);
}
