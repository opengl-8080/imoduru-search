package imoduru.domain;

/**
 * 入力情報.
 */
public class InputData {

    private SearchResult searchResult = new SearchResult();

    public void put(TableAlias tableAlias, TableSearchResult tableSearchResult) {
        this.searchResult.put(tableAlias, tableSearchResult);
    }

    public ColumnValues getColumnValues(TableAlias tableAlias, Column column) {
        return this.searchResult.getColumnValues(tableAlias, column);
    }

    public SearchValue getSearchValue(InputParameterName inputParameterName) {
        return null;
    }
}
