package imoduru.domain;

import imoduru.util.DateUtil;

/**
 * 今日日付.
 */
public class TodayFunction implements FunctionValue {

    @Override
    public BindParameter createBindParameter(InputData inputData) {
        return new SingleBindParameter(DateUtil.today());
    }
}
