package imoduru.domain;

import lombok.Value;
import org.apache.commons.lang3.text.StrBuilder;

/**
 * 完全一致.
 */
@Value
public class EqualTo extends ColumnSearchCondition {
    SearchValue searchValue;

    public EqualTo(Column column, SearchValue searchValue) {
        super(column);
        this.searchValue = searchValue;
    }

    @Override
    public ConditionExpression createConditionExpression(InputData inputData) {
        BindParameter bindParameter = this.searchValue.createBindParameter(inputData);

        int size = bindParameter.size();
        StrBuilder sql = new StrBuilder();

        for (int i=0; i<size; i++) {
            sql.appendSeparator(" OR ")
                    .append(this.column.getName()).append(" = ?");
        }

        return new ConditionExpression(new SqlStatement("(" + sql + ")"), bindParameter);
    }
}
