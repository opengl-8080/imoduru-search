package imoduru.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;

/**
 * カラム.
 */
@Value
public class Column {
    @Getter(AccessLevel.PACKAGE)
    String name;
}
