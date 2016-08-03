package imoduru.domain;

import lombok.Value;

/**
 * 完全一致.
 */
@Value
public class EqualTo implements CompareMethod {
    SearchValue searchValue;

    @Override
    public SqlStatement createSqlStatement(Column column, InputData inputData) {
        return null;
    }
}
