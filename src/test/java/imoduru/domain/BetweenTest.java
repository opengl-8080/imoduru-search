package imoduru.domain;

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
        assertThat(conditionExpression.getSqlStatement())
                .as("SQL文").isEqualTo(new SqlStatement("FOO BETWEEN ? AND ?"));

        MultiBindParameter expectedBindParameter = new MultiBindParameter(Arrays.asList(fromParameter, toParameter));
        assertThat(conditionExpression.getBindParameter())
                .as("埋め込みパラメータ").isEqualTo(expectedBindParameter);

    }
}