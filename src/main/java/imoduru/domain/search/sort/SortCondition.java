package imoduru.domain.search.sort;

import imoduru.domain.table.Column;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;

/**
 * ソート条件.
 */
@Value
public class SortCondition {
    @Getter(AccessLevel.PACKAGE)
    Column column;
    @Getter(AccessLevel.PACKAGE)
    SortDirection sortDirection;

    /**
     * ソートのための生SQLを取得する.
     * @return 生SQL
     */
    public String getRawSql() {
        return this.column.getName() + " " + this.sortDirection.name();
    }
}
