package imoduru.domain.search.value;

import imoduru.domain.search.expression.BindParameter;
import imoduru.domain.search.expression.SingleBindParameter;
import imoduru.domain.search.value.LastMonthLastDayFunction;
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
        LocalDate today = LocalDate.of(2016, 3, 10);
        new Expectations() {{
            DateUtil.today(); result = today;
        }};

        LastMonthLastDayFunction lastMonthFinalDateFunction = new LastMonthLastDayFunction();

        // exercise
        BindParameter actual = lastMonthFinalDateFunction.createBindParameter(null);

        // verify
        LocalDate lastMonthLastDate = LocalDate.of(2016, 2, 29);
        BindParameter expected = new SingleBindParameter(lastMonthLastDate);

        assertThat(actual).isEqualTo(expected);
    }
}