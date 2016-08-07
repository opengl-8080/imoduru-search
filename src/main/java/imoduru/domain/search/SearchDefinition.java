package imoduru.domain.search;

import imoduru.domain.search.input.InputParameterDefinition;
import lombok.RequiredArgsConstructor;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.Lists;

/**
 * 検索定義.
 */
@RequiredArgsConstructor
public class SearchDefinition {
    private final SearchDefinitionName name;
    private InputParameterDefinition inputParameterDefinition = new InputParameterDefinition(Lists.immutable.empty());
    private ImmutableList<SearchDefinitionDetail> searchDefinitionDetailList;

    public void search(SearchResultCollector collector, Context context) {
        this.searchDefinitionDetailList.each(it -> it.search(collector, context));
    }

    public void setInputParameterDefinition(InputParameterDefinition inputParameterDefinition) {
        this.inputParameterDefinition = inputParameterDefinition;
    }

    void setSearchDefinitionDetailList(ImmutableList<SearchDefinitionDetail> searchDefinitionDetails) {
        this.searchDefinitionDetailList = searchDefinitionDetails;
    }
}
