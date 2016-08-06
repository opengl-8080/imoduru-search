package imoduru.domain;

import org.eclipse.collections.impl.factory.Maps;

/**
 * 入力情報.
 */
public class InputData {

    private SearchResult searchResult = new SearchResult();
    private InputParameters inputParameters = new InputParameters(Maps.immutable.empty());

    /**
     * テーブル別名を指定して、検索結果を追加する.
     * @param tableAlias テーブル別名
     * @param tableSearchResult テーブル検索結果
     */
    public void put(TableAlias tableAlias, TableSearchResult tableSearchResult) {
        this.searchResult.put(tableAlias, tableSearchResult);
    }

    /**
     * 検索結果から、指定したカラムの値だけを抽出して一覧にして返す.
     * @param tableAlias テーブル別名
     * @param column カラム
     * @return 指定カラムの値一覧
     */
    public ColumnValues getColumnValues(TableAlias tableAlias, Column column) {
        return this.searchResult.getColumnValues(tableAlias, column);
    }

    /**
     * 入力パラメータの値を検索値として取得する.
     * @param inputParameterName 入力パラメータ名
     * @return 検索値
     */
    public SearchValue getInputParameterAsSearchValue(InputParameterName inputParameterName) {
        return this.inputParameters.getSearchValue(inputParameterName);
    }

    /**
     * 指定した入力パラメータ一覧を追加した、新しい入力データを生成する.
     * @param inputParameters 追加する入力パラメータ一覧
     * @return 入力パラメータ一覧を追加した新しい入力データ
     */
    public InputData newContext(InputParameters inputParameters) {
        InputData inputData = new InputData();
        inputData.searchResult = this.searchResult;
        inputData.inputParameters = this.inputParameters.addAll(inputParameters);

        return inputData;
    }

    void setInputParameters(InputParameters inputParameters) {
        this.inputParameters = inputParameters;
    }

    InputParameters getInputParameters() {
        return this.inputParameters;
    }

    SearchResult getSearchResult() {
        return this.searchResult;
    }
}
