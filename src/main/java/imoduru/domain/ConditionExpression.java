package imoduru.domain;

import lombok.Value;

/**
 * 条件式.
 */
@Value
public class ConditionExpression {
    SqlStatement sqlStatement;
    BindParameter bindParameter;
}
