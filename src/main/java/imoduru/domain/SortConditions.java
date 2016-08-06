package imoduru.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;
import org.eclipse.collections.api.list.ImmutableList;

/**
 * ソート条件一覧.
 */
@Value
public class SortConditions {
    @Getter(AccessLevel.PACKAGE)
    ImmutableList<SortCondition> list;

    /**
     * 全ソート条件の SQL を連結した生の SQL を取得する.
     * @return 生SQL
     */
    public String getRawSql() {
        return this.list.collect(SortCondition::getRawSql).makeString(", ");
    }
}
