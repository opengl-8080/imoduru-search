package imoduru.domain;

import lombok.Value;

@Value
public class ColumnSearchCondition {
    Column column;
    CompareMethod compareMethod;

    public ConditionExpression createConditionExpression(InputInformation inputInformation) {
        SqlStatement sqlStatement = new SqlStatement(this.column.getName() + " = ?");
        SearchValue searchValue = this.compareMethod.getSearchValue();
        BindParameter bindParameter = searchValue.createBindParameter(inputInformation);
        return new ConditionExpression(sqlStatement, bindParameter);
    }
}
