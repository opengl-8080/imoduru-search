package imoduru.domain;

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
        ConditionExpression conditionExpression = equalTo.createConditionExpression(new InputData());

        // verify
        assertThat(conditionExpression.getSqlStatement())
                .as("SQL文").isEqualTo(new SqlStatement("(FOO = ?)"));
        assertThat(conditionExpression.getBindParameter())
                .as("埋め込みパラメータ").isEqualTo(bindParameter);
    }

    @Test
    public void 複数埋め込みパラメータの場合_埋め込みパラメータのサイズ文だけ_OR_で連結されたSQL文が生成される() throws Exception {
        // setup
        BindParameter bindParameter = new MultiBindParameter(Arrays.asList(1, 2, 3));
        SearchValue searchValue = (i) -> bindParameter;
        EqualTo equalTo = new EqualTo(new Column("BAR"), searchValue);

        // exercise
        ConditionExpression conditionExpression = equalTo.createConditionExpression(new InputData());

        // verify
        assertThat(conditionExpression.getSqlStatement())
                .as("SQL文").isEqualTo(new SqlStatement("(BAR = ? OR BAR = ? OR BAR = ?)"));
        assertThat(conditionExpression.getBindParameter())
                .as("埋め込みパラメータ").isEqualTo(bindParameter);
    }
}