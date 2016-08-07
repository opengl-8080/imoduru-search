package imoduru.domain.search;

import imoduru.domain.SearchResultCollector;
import imoduru.domain.search.input.InputParameters;
import lombok.RequiredArgsConstructor;

/**
 * 埋め込み検索定義.
 */
@RequiredArgsConstructor
public class EmbeddedSearchDefinition implements SearchDefinitionDetail {
    private final SearchDefinition searchDefinition;
    private InputParameters inputParameters;

    @Override
    public void search(SearchResultCollector collector, Context context) {
        Context newContext = context.newContext(this.inputParameters);
        this.searchDefinition.search(collector, newContext);
    }

    public void setInputParameters(InputParameters inputParameters) {
        this.inputParameters = inputParameters;
    }
}
