package imoduru.domain;

/**
 * 比較方法.
 */
public interface CompareMethod {
    SqlStatement createSqlStatement(Column column, InputData inputData);
}
