package imoduru.domain;

import lombok.Value;

/**
 * 入力パラメータ.
 */
@Value
public class InputParameter implements SearchValue {
    InputParameterName inputParameterName;

    @Override
    public BindParameter createBindParameter(InputInformation inputInformation) {
        SearchValue searchValue = inputInformation.getSearchValue(this.inputParameterName);
        return searchValue.createBindParameter(inputInformation);
    }
}
