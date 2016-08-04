package imoduru.domain;

import lombok.Value;
import org.eclipse.collections.api.list.ImmutableList;

/**
 * テーブル検索結果.
 */
@Value
public class TableSearchResult {
    ImmutableList<Record> records;

    public ColumnValues getColumnValues(Column column) {
        return new ColumnValues(this.records.collect(record -> record.get(column)));
    }
}
