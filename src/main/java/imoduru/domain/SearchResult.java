package imoduru.domain;

import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.factory.Maps;

/**
 * 検索結果.
 */
public class SearchResult {
    MutableMap<TableAlias, TableSearchResult> tableSearchResultMap = Maps.mutable.empty();

    public void put(TableAlias table, TableSearchResult tableSearchResult) {
        this.tableSearchResultMap.put(table, tableSearchResult);
    }

    public ColumnValues get(TableAlias tableAlias, Column column) {
        TableSearchResult tableSearchResult = this.tableSearchResultMap.get(tableAlias);
        return tableSearchResult.getColumnValues(column);
    }
}
