package imoduru.domain.search.value;

import imoduru.domain.search.Context;
import imoduru.domain.search.expression.BindParameter;
import imoduru.domain.search.expression.SingleBindParameter;
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
