package imoduru.domain;

/**
 * 検索定義明細.
 */
public interface SearchDefinitionDetail {
    /**
     * 条件式一覧を生成する.
     * @param inputData 入力データ
     * @return 条件式一覧
     */
    ConditionExpressions createConditionExpressions(InputData inputData);
}
