package imoduru.domain;

import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.factory.Maps;

/**
 * 検索結果.
 */
public class SearchResult {
    private MutableMap<TableAlias, TableSearchResult> tableSearchResultMap = Maps.mutable.empty();

    /**
     * テーブルの検索結果を追加する.
     * @param table テーブル
     * @param tableSearchResult 検索結果
     */
    public void put(TableAlias table, TableSearchResult tableSearchResult) {
        this.tableSearchResultMap.put(table, tableSearchResult);
    }

    /**
     * 検索結果から指定したカラムの値だけを抽出して一覧で返す.
     * @param tableAlias テーブル
     * @param column カラム
     * @return 抽出されたカラム値の一覧
     */
    public ColumnValues getColumnValues(TableAlias tableAlias, Column column) {
        TableSearchResult tableSearchResult = this.tableSearchResultMap.get(tableAlias);
        return tableSearchResult.getColumnValues(column);
    }
}
