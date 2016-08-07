package imoduru.domain.search.condition;

import imoduru.domain.search.Context;
import imoduru.domain.search.expression.BindParameter;
import imoduru.domain.search.expression.ConditionExpression;
import imoduru.domain.search.expression.MultiBindParameter;
import imoduru.domain.search.expression.SingleBindParameter;
import imoduru.domain.search.expression.SqlStatement;
import imoduru.domain.search.value.SearchValue;
import imoduru.domain.table.Column;
import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

public class EqualToTest {

    @Test
    public void 単一埋め込みパラメータの場合_イコールで単一値と比較するSQL文が生成される() throws Exception {
        // setup
        BindParameter bindParameter = new SingleBindParameter(123);
        SearchValue searchValue = (i) -> bindParameter;
        EqualTo equalTo = new EqualTo(new Column("FOO"), searchValue);

        // exercise
        ConditionExpression conditionExpression = equalTo.createConditionExpression(new Context());

        // verify
        ConditionExpression expected = new ConditionExpression(new SqlStatement("(FOO = ?)"), bindParameter);

        assertThat(conditionExpression).isEqualTo(expected);
    }

    @Test
    public void 複数埋め込みパラメータの場合_埋め込みパラメータのサイズ文だけ_OR_で連結されたSQL文が生成される() throws Exception {
        // setup
        BindParameter a = new SingleBindParameter(1);
        BindParameter b = new SingleBindParameter(2);
        BindParameter c = new SingleBindParameter(3);
        BindParameter bindParameter = new MultiBindParameter(Arrays.asList(a, b, c));
        SearchValue searchValue = (i) -> bindParameter;
        EqualTo equalTo = new EqualTo(new Column("BAR"), searchValue);

        // exercise
        ConditionExpression conditionExpression = equalTo.createConditionExpression(new Context());

        // verify
        ConditionExpression expected = new ConditionExpression(new SqlStatement("(BAR = ? OR BAR = ? OR BAR = ?)"), bindParameter);

        assertThat(conditionExpression).isEqualTo(expected);
    }
}