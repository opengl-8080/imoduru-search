package imoduru.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class EqualToTest {

    @Test
    public void 埋め込みパラメータが単一値を返す場合_イコールで単一値と比較するSQL文が生成される() throws Exception {
        // setup
        SearchValue searchValue = (i) -> new SingleBindParameter(123);
        EqualTo equalTo = new EqualTo(searchValue);

        // exercise
        SqlStatement sqlStatement = equalTo.createSqlStatement(new Column("FOO"), new InputData());

        // verify
        assertThat(sqlStatement).isEqualTo(new SqlStatement("FOO = ?"));
    }
}