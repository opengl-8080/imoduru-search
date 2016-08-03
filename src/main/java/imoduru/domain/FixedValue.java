package imoduru.domain;

import lombok.Value;

/**
 * 固定値.
 */
@Value
public class FixedValue implements SingleSearchValue {

    Object value;

    @Override
    public BindParameter createBindParameter(InputData inputData) {
        return new SingleBindParameter(this.value);
    }
}
