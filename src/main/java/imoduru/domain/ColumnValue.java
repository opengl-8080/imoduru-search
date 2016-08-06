package imoduru.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;

/**
 * カラム値.
 */
@Value
public class ColumnValue {
    @Getter(AccessLevel.PACKAGE)
    Object value;
}
