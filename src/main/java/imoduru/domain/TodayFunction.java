package imoduru.domain;

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
