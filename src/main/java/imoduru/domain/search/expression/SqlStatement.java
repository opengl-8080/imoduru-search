package imoduru.domain.search.expression;

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
