package imoduru.domain;

import mockit.Expectations;
import mockit.Mocked;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class InputParameterTest {

    @Mocked
    private InputInformation inputInformation;

    @Test
    public void name() throws Exception {
        // setup
        InputParameterName name = new InputParameterName("p1");
        InputParameter inputParameter = new InputParameter(name);

        FixedValue searchValue = new FixedValue("abc");

        new Expectations() {{
            inputInformation.getSearchValue(name); result = searchValue;
        }};

        // exercise
        BindParameter bindParameter = inputParameter.createBindParameter(inputInformation);

        // verify
        assertThat(bindParameter).isEqualTo(new BindParameter(searchValue.getValue()));

    }
}