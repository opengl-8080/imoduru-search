package imoduru.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.factory.Maps;

/**
 * 入力パラメータ一覧.
 */
@ToString
@EqualsAndHashCode
public class InputParameters {
    private final MutableMap<InputParameterName, SearchValue> parameterMap = Maps.mutable.of();

    public void put(InputParameterName name, SearchValue searchValue) {
        this.parameterMap.put(name, searchValue);
    }

    public void addAll(InputParameters other) {
        other.parameterMap.forEachKeyValue(this.parameterMap::put);
    }

    public SearchValue getSearchValue(InputParameterName inputParameterName) {
        return this.parameterMap.get(inputParameterName);
    }
}
