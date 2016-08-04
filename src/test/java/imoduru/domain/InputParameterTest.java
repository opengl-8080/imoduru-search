package imoduru.domain;

import mockit.Expectations;
import mockit.Mocked;
import org.junit.Test;

import static imoduru.test.TestConstant.*;
import static org.assertj.core.api.Assertions.*;

public class InputParameterTest {

    @Mocked
    private InputData inputData;

    @Test
    public void name() throws Exception {
        // setup
        InputParameterName name = new InputParameterName(SEARCH_DEFINITION_NAME_A, "p1");

        BindParameter expected = new SingleBindParameter("123");
        SearchValue searchValue = (i) -> expected;

        new Expectations() {{
            inputData.getInputParameterAsSearchValue(name); result = searchValue;
        }};

        InputParameter inputParameter = new InputParameter(name);

        // exercise
        BindParameter actual = inputParameter.createBindParameter(inputData);

        // verify
        assertThat(actual).isEqualTo(expected);

    }
}