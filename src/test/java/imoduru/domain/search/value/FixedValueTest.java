package imoduru.domain.search.value;

import imoduru.domain.search.Context;
import imoduru.domain.search.expression.BindParameter;
import imoduru.domain.search.expression.SingleBindParameter;
import imoduru.domain.search.value.FixedValue;
import mockit.Mocked;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class FixedValueTest {

    @Mocked @SuppressWarnings("unused")
    private Context context;

    @Test
    public void 固定値と同じ値をもつ埋め込みパラメータが生成される() throws Exception {
        // setup
        FixedValue fixedValue = new FixedValue("foo");

        // exercise
        BindParameter bindParameter = fixedValue.createBindParameter(context);

        // verify
        assertThat(bindParameter).isEqualTo(new SingleBindParameter("foo"));
    }
}