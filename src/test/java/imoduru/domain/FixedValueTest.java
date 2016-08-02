package imoduru.domain;

import mockit.Mocked;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class FixedValueTest {

    @Mocked
    private InputInformation inputInformation;

    @Test
    public void 固定値と同じ値をもつ埋め込みパラメータが生成される() throws Exception {
        // setup
        FixedValue fixedValue = new FixedValue("foo");

        // exercise
        BindParameter bindParameter = fixedValue.createBindParameter(inputInformation);

        // verify
        assertThat(bindParameter).isEqualTo(new BindParameter("foo"));
    }
}