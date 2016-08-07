package imoduru.test;

import imoduru.domain.Column;
import imoduru.domain.ColumnValue;
import imoduru.domain.Record;
import imoduru.domain.SearchResult;
import imoduru.domain.Table;
import imoduru.domain.TableAlias;
import imoduru.domain.TableSearchResult;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Maps;

import static imoduru.test.TestConstant.*;

public class SearchResultBuilder {

    private SearchResult searchResult = new SearchResult();
    private TableAlias tableAlias;
    private MutableList<Record> records;

    public SearchResultBuilder table(String tableName) {
        if (this.tableAlias != null) {
            this.searchResult.put(this.tableAlias, new TableSearchResult(this.records.toImmutable()));
        }

        this.tableAlias = new TableAlias(new Table(tableName), tableName);
        this.records = Lists.mutable.of();
        return this;
    }

    public SearchResultBuilder record(int id, String name, String value) {
        ImmutableMap<Column, ColumnValue> columnValueMap = Maps.immutable.of(
                ID, new ColumnValue(id),
                NAME, new ColumnValue(name),
                VALUE, new ColumnValue(value)
        );
        this.records.add(new Record(columnValueMap));
        return this;
    }

    public SearchResult build() {
        if (this.tableAlias != null) {
            this.searchResult.put(this.tableAlias, new TableSearchResult(this.records.toImmutable()));
        }
        return this.searchResult;
    }
}
