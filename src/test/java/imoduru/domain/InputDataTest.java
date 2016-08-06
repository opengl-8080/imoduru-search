package imoduru.domain;

import org.eclipse.collections.impl.factory.Maps;
import org.junit.Test;

import static imoduru.test.TestConstant.*;
import static org.assertj.core.api.Assertions.*;

public class InputDataTest {

    @Test
    public void 指定したパラメータ一覧を追加した新しいインスタンスを生成できる() throws Exception {
        // setup
        InputData inputData = new InputData();

        InputParameters inputParameters1 = new InputParameters(Maps.immutable.of(
                INPUT_PARAMETER_NAME_1, new FixedValue("aaa"),
                INPUT_PARAMETER_NAME_2, new FixedValue("bbb")
        ));

        inputData.setInputParameters(inputParameters1);

        InputParameters inputParameters2 = new InputParameters(Maps.immutable.of(
                INPUT_PARAMETER_NAME_3, new FixedValue("ccc")
        ));

        // exercise
        InputData newContext = inputData.newContext(inputParameters2);

        // verify
        InputParameters expected = new InputParameters(Maps.immutable.of(
                INPUT_PARAMETER_NAME_1, new FixedValue("aaa"),
                INPUT_PARAMETER_NAME_2, new FixedValue("bbb"),
                INPUT_PARAMETER_NAME_3, new FixedValue("ccc")
        ));

        assertThat(newContext.getInputParameters()).as("入力パラメータ").isEqualTo(expected);
        assertThat(newContext.getSearchResult()).as("検索結果").isSameAs(inputData.getSearchResult());
    }

    @Test
    public void パラメータ名を指定して検索値を取得できる() throws Exception {
        // setup
        InputParameters inputParameters = new InputParameters(Maps.immutable.of(
            INPUT_PARAMETER_NAME_1, new FixedValue("aaa"),
            INPUT_PARAMETER_NAME_2, new FixedValue("bbb")
        ));

        InputData inputData = new InputData();
        inputData.setInputParameters(inputParameters);

        // exercise
        SearchValue searchValue = inputData.getInputParameterAsSearchValue(INPUT_PARAMETER_NAME_1);

        // verify
        assertThat(searchValue).isEqualTo(new FixedValue("aaa"));
    }
}