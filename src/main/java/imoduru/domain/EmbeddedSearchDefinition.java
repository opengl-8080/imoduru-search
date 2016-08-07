package imoduru.domain;

import lombok.RequiredArgsConstructor;

/**
 * 埋め込み検索定義.
 */
@RequiredArgsConstructor
public class EmbeddedSearchDefinition implements SearchDefinitionDetail {
    private final SearchDefinition searchDefinition;
    private InputParameters inputParameters;

    @Override
    public void search(SearchResultCollector collector, InputData inputData) {
        InputData newContext = inputData.newContext(this.inputParameters);
        this.searchDefinition.search(collector, newContext);
    }

    public void setInputParameters(InputParameters inputParameters) {
        this.inputParameters = inputParameters;
    }
}
