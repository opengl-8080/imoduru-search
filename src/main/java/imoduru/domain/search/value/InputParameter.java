package imoduru.domain.search.value;

import imoduru.domain.search.Context;
import imoduru.domain.search.expression.BindParameter;
import imoduru.domain.search.input.InputParameterName;
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
    public BindParameter createBindParameter(Context context) {
        SearchValue searchValue = context.getInputParameterAsSearchValue(this.inputParameterName);
        return searchValue.createBindParameter(context);
    }
}
