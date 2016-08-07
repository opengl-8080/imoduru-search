package imoduru.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;

/**
 * 固定値.
 */
@Value
public class FixedValue implements SingleSearchValue {
    @Getter(AccessLevel.PACKAGE)
    Object value;

    @Override
    public BindParameter createBindParameter(Context context) {
        return new SingleBindParameter(this.value);
    }
}
