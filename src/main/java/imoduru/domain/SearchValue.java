package imoduru.domain;

/**
 * 検索値.
 */
public interface SearchValue {

    /**
     * この検索値が持つ値をもとに、埋め込みパラメータを生成する.
     * @param inputData 入力データ
     * @return 埋め込みパラメータ
     */
    BindParameter createBindParameter(InputData inputData);
}
