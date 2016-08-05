package imoduru.domain;

import lombok.Value;
import org.eclipse.collections.api.list.ImmutableList;

/**
 * ソート条件一覧.
 */
@Value
public class SortConditions {
    ImmutableList<SortCondition> list;

    public String getRawSql() {
        return this.list.collect(SortCondition::getRawSql).makeString(", ");
    }
}
