package imoduru.domain;

import lombok.Value;

/**
 * ソート条件.
 */
@Value
public class SortCondition {
    Column column;
    SortDirection sortDirection;

    public String getRawSql() {
        return this.column.getName() + " " + this.sortDirection.name();
    }
}
