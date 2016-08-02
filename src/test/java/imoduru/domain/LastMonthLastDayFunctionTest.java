package imoduru.domain;

import imoduru.util.DateUtil;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

public class LastMonthLastDayFunctionTest {

    @Mocked @SuppressWarnings("unused")
    private DateUtil dateUtil;

    @Test
    public void 先月最終日が取得できる() throws Exception {
        // setup
        LocalDate localDate = LocalDate.of(2016, 3, 10);
        new Expectations() {{
            DateUtil.today(); result = localDate;
        }};

        LastMonthLastDayFunction lastMonthFinalDateFunction = new LastMonthLastDayFunction();

        // exercise
        BindParameter bindParameter = lastMonthFinalDateFunction.createBindParameter(null);

        // verify
        assertThat(bindParameter).isEqualTo(new BindParameter(LocalDate.of(2016, 2, 29)));
    }
}