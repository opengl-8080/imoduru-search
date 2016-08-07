package imoduru.domain.search.input;

import imoduru.domain.search.value.SearchValue;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;
import org.eclipse.collections.api.map.ImmutableMap;

/**
 * 入力パラメータ一覧.
 */
@Value
public class InputParameters {
    @Getter(AccessLevel.PACKAGE)
    ImmutableMap<InputParameterName, SearchValue> parameterMap;

    public SearchValue getSearchValue(InputParameterName inputParameterName) {
        return this.parameterMap.get(inputParameterName);
    }

    public InputParameters addAll(InputParameters other) {
        return new InputParameters(this.parameterMap.newWithAllKeyValues(other.parameterMap.keyValuesView()));
    }
}
