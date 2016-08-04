package imoduru.domain;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Maps;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class SearchResultTest {

    private static final Column ID = new Column("ID");
    private static final Column NAME = new Column("NAME");
    private static final Column VALUE = new Column("VALUE");

    @Test
    public void テーブル別名とカラムで検索結果の値を取得できる() throws Exception {
        // setup
        SearchResult searchResult =
                 table("FOO")
                .record(1, "name1", "value1")
                .record(2, "name2", "value2")
                .record(3, "name3", "value3")
                .table("BAR")
                .record(4, "name4", "value4")
                .record(5, "name5", "value5")
                .record(6, "name6", "value6")
                .build();

        // exercise
        ColumnValues columnValues = searchResult.get(new TableAlias("FOO"), NAME);

        // verify
        assertThat(columnValues.getValues()).containsExactly(
            new ColumnValue("name1"),
            new ColumnValue("name2"),
            new ColumnValue("name3")
        );
    }

    private static SearchResultBuilder table(String name) {
        return new SearchResultBuilder().table(name);
    }

    private static class SearchResultBuilder {

        private SearchResult searchResult = new SearchResult();
        private TableAlias tableAlias;
        private MutableList<Record> records;

        private SearchResultBuilder table(String tableName) {
            if (this.tableAlias != null) {
                this.searchResult.put(this.tableAlias, new TableSearchResult(this.records.toImmutable()));
            }

            this.tableAlias = new TableAlias(tableName);
            this.records = Lists.mutable.of();
            return this;
        }

        private SearchResultBuilder record(int id, String name, String value) {
            ImmutableMap<Column, ColumnValue> columnValueMap = Maps.immutable.of(
                    ID, new ColumnValue(id),
                    NAME, new ColumnValue(name),
                    VALUE, new ColumnValue(value)
            );
            this.records.add(new Record(columnValueMap));
            return this;
        }

        private SearchResult build() {
            return this.searchResult;
        }
    }
}