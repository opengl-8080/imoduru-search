package imoduru.test;

import imoduru.domain.table.Column;
import imoduru.domain.search.result.ColumnValue;
import imoduru.domain.search.result.Record;
import imoduru.domain.search.result.SearchResult;
import imoduru.domain.table.Table;
import imoduru.domain.table.TableAlias;
import imoduru.domain.search.result.TableSearchResult;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Maps;

import static imoduru.test.TestConstant.*;

public class SearchResultBuilder {

    private SearchResult searchResult = new SearchResult();
    private TableAlias tableAlias;
    private MutableList<Record> records;

    public SearchResultBuilder table(TableAlias tableAlias) {
        if (this.tableAlias != null) {
            this.searchResult.put(this.tableAlias, new TableSearchResult(this.records.toImmutable()));
        }

        this.tableAlias = tableAlias;
        this.records = Lists.mutable.of();
        return this;
    }

    public SearchResultBuilder table(String tableName) {
        return this.table(new TableAlias(new Table(tableName), tableName));
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
