package imoduru.domain;

public abstract class ColumnSearchCondition {
    protected final Column column;

    public ColumnSearchCondition(Column column) {
        this.column = column;
    }

    public abstract ConditionExpression createConditionExpression(InputData inputData);
}
