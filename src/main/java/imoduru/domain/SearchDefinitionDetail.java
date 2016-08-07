package imoduru.domain;

/**
 * 検索定義明細.
 */
public interface SearchDefinitionDetail {
    /**
     * 自身が持つ検索条件をもとに、指定したコレクターに検索を依頼する.
     * @param collector 検索結果コレクター
     * @param inputData 入力データ
     */
    void search(SearchResultCollector collector, InputData inputData);
}
