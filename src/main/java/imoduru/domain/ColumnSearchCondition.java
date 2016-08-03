package imoduru.domain;

import lombok.Value;

@Value
public class ColumnSearchCondition {
    Column column;
    CompareMethod compareMethod;

    public ConditionExpression createConditionExpression(InputData inputData) {
//        SqlStatement sqlStatement = new SqlStatement(this.column.getName() + " = ?");
//        SearchValue searchValue = this.compareMethod.getSearchValue();
//        BindParameter bindParameter = searchValue.createBindParameter(inputData);
//        return new ConditionExpression(sqlStatement, bindParameter);
        return null;
    }
}
