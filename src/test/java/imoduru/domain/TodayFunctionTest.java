package imoduru.domain;

import imoduru.util.DateUtil;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

public class TodayFunctionTest {

    @Mocked @SuppressWarnings("unused")
    private DateUtil dateUtil;

    @Test
    public void システム日付を持った埋め込みパラメータが生成される() throws Exception {
        // setup
        LocalDate today = LocalDate.of(2016, 1, 2);
        new Expectations() {{
            DateUtil.today(); result = today;
        }};

        TodayFunction todayFunction = new TodayFunction();

        // exercise
        BindParameter bindParameter = todayFunction.createBindParameter(null);

        // verify
        assertThat(bindParameter).isEqualTo(new SingleBindParameter(today));
    }
}