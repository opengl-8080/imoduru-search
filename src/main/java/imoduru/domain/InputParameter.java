package imoduru.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;

/**
 * 入力パラメータ.
 */
@Value
public class InputParameter implements MultiSearchValue {
    @Getter(AccessLevel.PACKAGE)
    InputParameterName inputParameterName;

    @Override
    public BindParameter createBindParameter(InputData inputData) {
        SearchValue searchValue = inputData.getInputParameterAsSearchValue(this.inputParameterName);
        return searchValue.createBindParameter(inputData);
    }
}
