package imoduru.domain.search.value;

import imoduru.domain.search.Context;
import imoduru.domain.search.expression.BindParameter;
import imoduru.domain.search.expression.SingleBindParameter;
import imoduru.domain.search.input.InputParameterName;
import imoduru.domain.search.value.InputParameter;
import imoduru.domain.search.value.SearchValue;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Test;

import static imoduru.test.TestConstant.*;
import static org.assertj.core.api.Assertions.*;

public class InputParameterTest {

    @Mocked
    private Context context;

    @Test
    public void name() throws Exception {
        // setup
        InputParameterName name = new InputParameterName(SEARCH_DEFINITION_NAME_A, "p1");

        BindParameter expected = new SingleBindParameter("123");
        SearchValue searchValue = (i) -> expected;

        new Expectations() {{
            context.getInputParameterAsSearchValue(name); result = searchValue;
        }};

        InputParameter inputParameter = new InputParameter(name);

        // exercise
        BindParameter actual = inputParameter.createBindParameter(context);

        // verify
        assertThat(actual).isEqualTo(expected);

    }
}