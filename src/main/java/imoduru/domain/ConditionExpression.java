package imoduru.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;

/**
 * 条件式.
 */
@Value
public class ConditionExpression {
    @Getter(AccessLevel.PACKAGE)
    SqlStatement sqlStatement;
    @Getter(AccessLevel.PACKAGE)
    BindParameter bindParameter;
}
