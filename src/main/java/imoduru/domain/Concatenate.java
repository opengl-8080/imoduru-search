package imoduru.domain;

import lombok.Value;

/**
 * 文字列連結.
 */
@Value
public class Concatenate implements MultiSearchValue {
    String prefix;
    SearchValue searchValue;

    @Override
    public BindParameter createBindParameter(InputData inputData) {
        BindParameter bindParameter = this.searchValue.createBindParameter(inputData);
        bindParameter.pushConverter(value -> this.prefix + value);
        return bindParameter;
    }
}
