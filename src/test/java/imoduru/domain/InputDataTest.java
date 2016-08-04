package imoduru.domain;

import org.junit.Test;

import static imoduru.test.TestConstant.*;
import static org.assertj.core.api.Assertions.*;

public class InputDataTest {

    @Test
    public void パラメータ一覧を追加できる() throws Exception {
        // setup
        InputParameters inputParameters1 = new InputParameters();
        inputParameters1.put(INPUT_PARAMETER_NAME_1, new FixedValue("aaa"));
        inputParameters1.put(INPUT_PARAMETER_NAME_2, new FixedValue("bbb"));

        InputParameters inputParameters2 = new InputParameters();
        inputParameters2.put(INPUT_PARAMETER_NAME_3, new FixedValue("ccc"));

        InputData inputData = new InputData();

        // exercise
        inputData.add(inputParameters1);
        inputData.add(inputParameters2);

        // verify
        InputParameters actual = inputData.getInputParameters();

        InputParameters expected = new InputParameters();
        expected.put(INPUT_PARAMETER_NAME_1, new FixedValue("aaa"));
        expected.put(INPUT_PARAMETER_NAME_2, new FixedValue("bbb"));
        expected.put(INPUT_PARAMETER_NAME_3, new FixedValue("ccc"));

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void パラメータ名を指定して検索値を取得できる() throws Exception {
        // setup
        InputParameters inputParameters = new InputParameters();
        inputParameters.put(INPUT_PARAMETER_NAME_1, new FixedValue("aaa"));
        inputParameters.put(INPUT_PARAMETER_NAME_2, new FixedValue("bbb"));

        InputData inputData = new InputData();
        inputData.add(inputParameters);

        // exercise
        SearchValue searchValue = inputData.getInputParameterAsSearchValue(INPUT_PARAMETER_NAME_1);

        // verify
        assertThat(searchValue).isEqualTo(new FixedValue("aaa"));
    }
}