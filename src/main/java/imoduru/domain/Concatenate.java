package imoduru.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;

/**
 * 文字列連結.
 */
@Value
public class Concatenate implements MultiSearchValue {
    @Getter(AccessLevel.PACKAGE)
    String prefix;
    @Getter(AccessLevel.PACKAGE)
    SearchValue searchValue;

    @Override
    public BindParameter createBindParameter(Context context) {
        BindParameter bindParameter = this.searchValue.createBindParameter(context);
        bindParameter.pushConverter(value -> this.prefix + value);
        return bindParameter;
    }
}
