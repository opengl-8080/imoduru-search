package imoduru.domain;

/**
 * 入力情報.
 */
public class InputData {

    private SearchResult searchResult = new SearchResult();
    private InputParameters inputParameters = new InputParameters();

    public void put(TableAlias tableAlias, TableSearchResult tableSearchResult) {
        this.searchResult.put(tableAlias, tableSearchResult);
    }

    public ColumnValues getColumnValues(TableAlias tableAlias, Column column) {
        return this.searchResult.getColumnValues(tableAlias, column);
    }

    public SearchValue getInputParameterAsSearchValue(InputParameterName inputParameterName) {
        return this.inputParameters.getSearchValue(inputParameterName);
    }

    public void add(InputParameters inputParameters) {
        this.inputParameters.addAll(inputParameters);
    }

    InputParameters getInputParameters() {
        return inputParameters;
    }
}
