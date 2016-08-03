package imoduru.domain;

import lombok.Value;

/**
 * 入力パラメータ.
 */
@Value
public class InputParameter implements SearchValue {
    InputParameterName inputParameterName;

    @Override
    public BindParameter createBindParameter(InputData inputData) {
        SearchValue searchValue = inputData.getSearchValue(this.inputParameterName);
        return searchValue.createBindParameter(inputData);
    }
}
