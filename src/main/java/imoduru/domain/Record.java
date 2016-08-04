package imoduru.domain;

import lombok.Value;
import org.eclipse.collections.api.map.ImmutableMap;

/**
 * レコード.
 */
@Value
public class Record {
    ImmutableMap<Column, ColumnValue> columnValueMap;

    public ColumnValue get(Column column) {
        return this.columnValueMap.get(column);
    }
}
