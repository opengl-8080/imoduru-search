package imoduru.domain;

import org.eclipse.collections.impl.factory.Maps;

/**
 * コンテキスト.
 */
public class Context {

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
     * 指定した入力パラメータ一覧を追加した、新しいコンテキストを生成する.
     * @param inputParameters 追加する入力パラメータ一覧
     * @return 入力パラメータ一覧を追加した新しいコンテキスト
     */
    public Context newContext(InputParameters inputParameters) {
        Context context = new Context();
        context.searchResult = this.searchResult;
        context.inputParameters = this.inputParameters.addAll(inputParameters);

        return context;
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
