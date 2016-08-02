package imoduru.domain;

import lombok.Value;

/**
 * 固定値.
 */
@Value
public class FixedValue implements SearchValue {

    Object value;

    @Override
    public BindParameter createBindParameter(InputInformation inputInformation) {
        return new BindParameter(this.value);
    }
}
