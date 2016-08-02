package imoduru.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class ColumnSearchConditionTest {

    @Test
    public void 固定値の埋め込みパラメータ_完全一致() throws Exception {
        // setup
        InputInformation inputInformation = new InputInformation();
        Column column = new Column("FOO");
        FixedValue fixedValue = new FixedValue("abc");
        EqualTo equalTo = new EqualTo(fixedValue);
        ColumnSearchCondition columnSearchCondition = new ColumnSearchCondition(column, equalTo);

        // exercise
        ConditionExpression conditionExpression = columnSearchCondition.createConditionExpression(inputInformation);

        // verify
        SqlStatement sqlStatement = conditionExpression.getSqlStatement();
        assertThat(sqlStatement.getValue()).as("SQL文").isEqualTo("FOO = ?");

        BindParameter bindParameter = conditionExpression.getBindParameter();
        assertThat(bindParameter.getValue()).as("埋め込みパラメータ").isEqualTo("abc");
    }
}