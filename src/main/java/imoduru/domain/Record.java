package imoduru.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;
import org.eclipse.collections.api.map.ImmutableMap;

/**
 * レコード.
 */
@Value
public class Record {
    @Getter(AccessLevel.PACKAGE)
    ImmutableMap<Column, ColumnValue> columnValueMap;

    public ColumnValue get(Column column) {
        return this.columnValueMap.get(column);
    }
}
