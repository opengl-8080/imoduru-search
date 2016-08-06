package imoduru.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;
import org.eclipse.collections.api.list.ImmutableList;

/**
 * テーブル検索結果.
 */
@Value
public class TableSearchResult {
    @Getter(AccessLevel.PACKAGE)
    ImmutableList<Record> records;

    /**
     * 指定したカラムの値だけを抽出して、一覧にして返す.
     * @param column カラム
     * @return 抽出したカラム値の一覧
     */
    public ColumnValues getColumnValues(Column column) {
        return new ColumnValues(this.records.collect(record -> record.get(column)));
    }
}
