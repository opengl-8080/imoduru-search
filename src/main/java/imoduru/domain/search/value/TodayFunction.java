package imoduru.domain.search.value;

import imoduru.domain.search.Context;
import imoduru.domain.search.expression.BindParameter;
import imoduru.domain.search.expression.SingleBindParameter;
import imoduru.util.DateUtil;

/**
 * 今日日付.
 */
public class TodayFunction implements FunctionValue {

    @Override
    public BindParameter createBindParameter(Context context) {
        return new SingleBindParameter(DateUtil.today());
    }
}
