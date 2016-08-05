package imoduru.domain;

import imoduru.test.TestConstant;
import mockit.Mocked;
import mockit.Verifications;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.utility.ArrayIterate;
import org.junit.Test;

import java.sql.PreparedStatement;

import static org.assertj.core.api.Assertions.*;

public class ConditionExpressionsTest {

    @Mocked
    private PreparedStatement ps;

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