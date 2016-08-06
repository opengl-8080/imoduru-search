package imoduru.domain;

import lombok.RequiredArgsConstructor;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;

/**
 * 検索定義.
 */
@RequiredArgsConstructor
public class SearchDefinition {
    private final SearchDefinitionName name;
    private InputParameterDefinition inputParameterDefinition = new InputParameterDefinition(Lists.immutable.empty());
    private MutableList<SearchDefinitionDetail> searchDefinitionDetailList = Lists.mutable.of();

    public ConditionExpressions createConditionExpressions(InputData inputData) {
        return null;
    }

    public void setInputParameterDefinition(InputParameterDefinition inputParameterDefinition) {
        this.inputParameterDefinition = inputParameterDefinition;
    }
}
