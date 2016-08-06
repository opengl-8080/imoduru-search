package imoduru.domain;

import imoduru.test.TestConstant;
import mockit.Mocked;
import mockit.Verifications;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.junit.Test;

import java.sql.PreparedStatement;

import static org.assertj.core.api.Assertions.*;

public class ConditionExpressionsTest {

    @Mocked
    private PreparedStatement ps;

    @Test
    public void 条件式一覧の連結ができる() throws Exception {
        // setup
        MutableList<ConditionExpression> expressionList1 = Lists.mutable.of(
                TestConstant.createConditionExpression("AAA", "a"),
                TestConstant.createConditionExpression("BBB", "b")
        );

        ConditionExpressions expressions1 = new ConditionExpressions(expressionList1.toImmutable());

        MutableList<ConditionExpression> expressionList2 = Lists.mutable.of(
                TestConstant.createConditionExpression("CCC", "c"),
                TestConstant.createConditionExpression("DDD", "d")
        );

        ConditionExpressions expressions2 = new ConditionExpressions(expressionList2.toImmutable());

        // exercise
        ConditionExpressions actual = expressions1.addAll(expressions2);

        // verify
        assertThat(actual.getExpressions()).containsExactly(
            TestConstant.createConditionExpression("AAA", "a"),
            TestConstant.createConditionExpression("BBB", "b"),
            TestConstant.createConditionExpression("CCC", "c"),
            TestConstant.createConditionExpression("DDD", "d")
        );
    }

    @Test
    public void 各条件式に対してパラメータの設定が順番に実行される() throws Exception {
        // setup
        MutableList<ConditionExpression> expressionList = Lists.mutable.of();

        ConditionExpression exp1 = TestConstant.createConditionExpression("AAA", "a", "c", "d");
        BindParameter bp1 = exp1.getBindParameter();
        expressionList.add(exp1);
        ConditionExpression exp2 = TestConstant.createConditionExpression("BBB", "b");
        BindParameter bp2 = exp2.getBindParameter();
        expressionList.add(exp2);

        ConditionExpressions expressions = new ConditionExpressions(expressionList.toImmutable());

        // exercise
        expressions.setParametersInto(ps);

        // verify
        new Verifications() {{
            bp1.setParameter(ps, 1); times = 1;
            bp2.setParameter(ps, 4); times = 1;
        }};
    }

    @Test
    public void 各条件式のSQLをANDで連結した文字列が取得できる() throws Exception {
        // setup
        MutableList<ConditionExpression> expressionList = Lists.mutable.of();

        expressionList.add(TestConstant.createConditionExpression("AAA", "a"));
        expressionList.add(TestConstant.createConditionExpression("BBB", "b"));

        ConditionExpressions expressions = new ConditionExpressions(expressionList.toImmutable());

        // exercise
        String rawSql = expressions.getRawSql();

        // verify
        assertThat(rawSql).isEqualTo("AAA AND BBB");

    }
}