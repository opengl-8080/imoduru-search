package imoduru.domain.search.result;

import imoduru.domain.table.Column;
import imoduru.domain.table.TableAlias;
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

    /**
     * 指定した検索結果が、この検索結果と等しい結果を持つかどうかを確認する.
     * @param searchResult 比較対象の検索結果
     * @return 結果が等しい場合は true
     */
    public boolean hasSameValue(SearchResult searchResult) {
        if (searchResult == null) {
            return false;
        }

        return this.tableSearchResultMap.equals(searchResult.tableSearchResultMap);
    }
}
