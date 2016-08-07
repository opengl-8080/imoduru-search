package imoduru.test;

import imoduru.domain.table.Column;
import imoduru.domain.search.result.ColumnValue;
import imoduru.domain.search.result.Record;
import imoduru.domain.search.result.TableSearchResult;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Maps;

import static imoduru.test.TestConstant.*;

public class TableSearchResultBuilder {
    private MutableList<Record> records = Lists.mutable.of();

    public TableSearchResultBuilder record(int id, String name, String value) {
        ImmutableMap<Column, ColumnValue> columnValueMap = Maps.immutable.of(
                ID, new ColumnValue(id),
                NAME, new ColumnValue(name),
                VALUE, new ColumnValue(value)
        );
        this.records.add(new Record(columnValueMap));
        return this;
    }

    public TableSearchResult build() {
        return new TableSearchResult(this.records.toImmutable());
    }
}
