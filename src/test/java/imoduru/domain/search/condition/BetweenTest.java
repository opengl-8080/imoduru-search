package imoduru.domain.search.condition;

import imoduru.domain.search.Context;
import imoduru.domain.search.expression.ConditionExpression;
import imoduru.domain.search.expression.MultiBindParameter;
import imoduru.domain.search.expression.SingleBindParameter;
import imoduru.domain.search.expression.SqlStatement;
import imoduru.domain.search.value.SingleSearchValue;
import imoduru.domain.table.Column;
import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

public class BetweenTest {

    @Test
    public void 条件式の生成() throws Exception {
        // setup
        SingleBindParameter fromParameter = new SingleBindParameter("a");
        SingleSearchValue from = (i) -> fromParameter;

        SingleBindParameter toParameter = new SingleBindParameter("b");
        SingleSearchValue to = (i) -> toParameter;

        Between between = new Between(new Column("FOO"), from, to);

        // exercise
        ConditionExpression conditionExpression = between.createConditionExpression(new Context());

        // verify
        ConditionExpression expected = new ConditionExpression(
            new SqlStatement("FOO BETWEEN ? AND ?"),
            new MultiBindParameter(Arrays.asList(fromParameter, toParameter))
        );

        assertThat(conditionExpression).isEqualTo(expected);
    }
}