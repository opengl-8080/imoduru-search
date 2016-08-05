package imoduru.domain;

import imoduru.test.TestConstant;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class TableSearchConditionTest {

    @Test
    public void カラム検索条件が生成した条件式をまとめて条件式一覧にした結果を取得できる() throws Exception {
        // setup
        Table table = new Table("FOO");
        TableAlias alias = new TableAlias("foo");
        TableSearchCondition tableSearchCondition = new TableSearchCondition(table, alias);

        Column barColumn = new Column("BAR");
        SearchValue valueA = new FixedValue("a");
        ColumnSearchCondition condition1 = new EqualTo(barColumn, valueA);

        tableSearchCondition.add(condition1);

        Column fizzColumn = new Column("FIZZ");
        SearchValue valueB = new FixedValue("b");
        ColumnSearchCondition condition2 = new EqualTo(fizzColumn, valueB);

        tableSearchCondition.add(condition2);

        // exercise
        ConditionExpressions conditionExpressions = tableSearchCondition.createConditionExpressions(new InputData());

        // verify
        assertThat(conditionExpressions.getExpressions()).containsExactly(
            TestConstant.createConditionExpression("(BAR = ?)", "a"),
            TestConstant.createConditionExpression("(FIZZ = ?)", "b")
        );
    }
}