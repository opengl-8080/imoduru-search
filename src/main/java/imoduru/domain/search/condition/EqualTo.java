package imoduru.domain.search.condition;

import imoduru.domain.search.Context;
import imoduru.domain.search.expression.BindParameter;
import imoduru.domain.search.expression.ConditionExpression;
import imoduru.domain.search.expression.SqlStatement;
import imoduru.domain.search.value.SearchValue;
import imoduru.domain.table.Column;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import org.apache.commons.lang3.text.StrBuilder;

/**
 * 完全一致.
 */
@Value
@EqualsAndHashCode(callSuper = true)
public class EqualTo extends ColumnSearchCondition {
    @Getter(AccessLevel.PACKAGE)
    SearchValue searchValue;

    public EqualTo(Column column, SearchValue searchValue) {
        super(column);
        this.searchValue = searchValue;
    }

    @Override
    public ConditionExpression createConditionExpression(Context context) {
        BindParameter bindParameter = this.searchValue.createBindParameter(context);

        int size = bindParameter.size();
        StrBuilder sql = new StrBuilder();

        for (int i=0; i<size; i++) {
            sql.appendSeparator(" OR ")
                    .append(this.column.getName()).append(" = ?");
        }

        return new ConditionExpression(new SqlStatement("(" + sql + ")"), bindParameter);
    }
}
