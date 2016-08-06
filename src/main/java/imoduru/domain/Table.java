package imoduru.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;

/**
 * テーブル.
 */
@Value
public class Table {
    @Getter(AccessLevel.PACKAGE)
    String name;
}
