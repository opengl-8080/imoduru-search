package imoduru.domain.search.value;

import imoduru.domain.search.Context;
import imoduru.domain.search.expression.BindParameter;
import imoduru.domain.search.expression.SingleBindParameter;
import imoduru.util.DateUtil;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 * 先月最終日関数.
 */
public class LastMonthLastDayFunction implements FunctionValue {

    @Override
    public BindParameter createBindParameter(Context context) {
        LocalDate today = DateUtil.today();
        LocalDate lastMonth = today.minusMonths(1);
        LocalDate lastMonthLastDay = lastMonth.with(TemporalAdjusters.lastDayOfMonth());

        return new SingleBindParameter(lastMonthLastDay);
    }
}
