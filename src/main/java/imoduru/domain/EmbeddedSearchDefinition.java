package imoduru.domain;

import lombok.RequiredArgsConstructor;

/**
 * 埋め込み検索定義.
 */
@RequiredArgsConstructor
public class EmbeddedSearchDefinition implements SearchDefinitionDetail {
    private final SearchDefinition searchDefinition;

    @Override
    public ConditionExpressions createConditionExpressions(InputData inputData) {
        return this.searchDefinition.createConditionExpressions(inputData);
    }
}
