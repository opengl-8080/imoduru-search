package imoduru.domain.search.input;

import imoduru.domain.search.value.FixedValue;
import org.eclipse.collections.impl.factory.Maps;
import org.junit.Test;

import static imoduru.test.TestConstant.*;
import static org.assertj.core.api.Assertions.*;

public class InputParametersTest {

    @Test
    public void 指定したパラメータ一覧を結合した新しい一覧を取得できる() throws Exception {
        // setup
        InputParameters parameters1 = new InputParameters(Maps.immutable.of(
            INPUT_PARAMETER_NAME_1, new FixedValue("abc")
        ));

        InputParameters parameters2 = new InputParameters(Maps.immutable.of(
            INPUT_PARAMETER_NAME_2, new FixedValue("def"),
            INPUT_PARAMETER_NAME_3, new FixedValue("ghi")
        ));

        // exercise
        InputParameters newParameters = parameters1.addAll(parameters2);

        // verify
        InputParameters expected = new InputParameters(Maps.immutable.of(
            INPUT_PARAMETER_NAME_1, new FixedValue("abc"),
            INPUT_PARAMETER_NAME_2, new FixedValue("def"),
            INPUT_PARAMETER_NAME_3, new FixedValue("ghi")
        ));

        assertThat(newParameters).isEqualTo(expected);
    }
}