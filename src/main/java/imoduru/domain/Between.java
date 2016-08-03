package imoduru.domain;

import java.util.Arrays;

/**
 * Between.
 */
public class Between extends ColumnSearchCondition {
    private final SingleSearchValue from;
    private final SingleSearchValue to;

    public Between(Column column, SingleSearchValue from, SingleSearchValue to) {
        super(column);
        this.from = from;
        this.to = to;
    }

    @Override
    public ConditionExpression createConditionExpression(InputData inputData) {
        SqlStatement sqlStatement = new SqlStatement(this.column.getName() + " BETWEEN ? AND ?");
        BindParameter fromParameter = this.from.createBindParameter(inputData);
        BindParameter toParameter = this.to.createBindParameter(inputData);

        return new ConditionExpression(sqlStatement, new MultiBindParameter(Arrays.asList(fromParameter, toParameter)));
    }
}
