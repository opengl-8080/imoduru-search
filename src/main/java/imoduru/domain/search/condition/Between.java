package imoduru.domain.search.condition;

import imoduru.domain.search.Context;
import imoduru.domain.search.expression.BindParameter;
import imoduru.domain.search.expression.ConditionExpression;
import imoduru.domain.search.expression.MultiBindParameter;
import imoduru.domain.search.expression.SqlStatement;
import imoduru.domain.search.value.SingleSearchValue;
import imoduru.domain.table.Column;

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
    public ConditionExpression createConditionExpression(Context context) {
        SqlStatement sqlStatement = new SqlStatement(this.column.getName() + " BETWEEN ? AND ?");
        BindParameter fromParameter = this.from.createBindParameter(context);
        BindParameter toParameter = this.to.createBindParameter(context);

        return new ConditionExpression(sqlStatement, new MultiBindParameter(Arrays.asList(fromParameter, toParameter)));
    }
}
