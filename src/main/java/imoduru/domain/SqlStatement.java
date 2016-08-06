package imoduru.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;

/**
 * SQL 文.
 */
@Value
public class SqlStatement {
    @Getter(AccessLevel.PACKAGE)
    String value;
}
