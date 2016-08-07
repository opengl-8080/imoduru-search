package imoduru.domain.search.result;

import imoduru.domain.table.TableAlias;
import imoduru.test.SearchResultBuilder;
import org.junit.Test;

import static imoduru.test.TestConstant.*;
import static org.assertj.core.api.Assertions.*;

public class SearchResultTest {
    @Test
    public void 検索結果が等しいかどうかを判定できる_比較相手がnullの場合() throws Exception {
        // setup
        SearchResult searchResult1 = this.createSearchResult();

        // exercise
        boolean actual = searchResult1.hasSameValue(null);

        // verify
        assertThat(actual).isFalse();
    }

    @Test
    public void 検索結果が等しいかどうかを判定できる_等しくない場合() throws Exception {
        // setup
        SearchResult searchResult1 = this.createSearchResult();
        SearchResult searchResult2 =
            table(TABLE_ALIAS_FOO)
                    .record(1, "name1", "value1")
                    .record(2, "name2", "value2")
                    .record(3, "name3", "value3")
                    .build();

        // exercise
        boolean actual = searchResult1.hasSameValue(searchResult2);

        // verify
        assertThat(actual).isFalse();
    }

    @Test
    public void 検索結果が等しいかどうかを判定できる_等しい場合() throws Exception {
        // setup
        SearchResult searchResult1 = this.createSearchResult();
        SearchResult searchResult2 = this.createSearchResult();

        // exercise
        boolean actual = searchResult1.hasSameValue(searchResult2);

        // verify
        assertThat(actual).isTrue();
    }

    @Test
    public void テーブル別名とカラムで検索結果の値を取得できる() throws Exception {
        // setup
        SearchResult searchResult = this.createSearchResult();

        // exercise
        ColumnValues fooColumnValues = searchResult.getColumnValues(TABLE_ALIAS_FOO, NAME);
        ColumnValues barColumnValues = searchResult.getColumnValues(TABLE_ALIAS_BAR, VALUE);

        // verify
        assertThat(fooColumnValues.getValues()).containsExactly(
            new ColumnValue("name1"),
            new ColumnValue("name2"),
            new ColumnValue("name3")
        );

        assertThat(barColumnValues.getValues()).containsExactly(
                new ColumnValue("value4"),
                new ColumnValue("value5"),
                new ColumnValue("value6")
        );
    }

    private SearchResult createSearchResult() {
        return
                table(TABLE_ALIAS_FOO)
                        .record(1, "name1", "value1")
                        .record(2, "name2", "value2")
                        .record(3, "name3", "value3")
                        .table(TABLE_ALIAS_BAR)
                        .record(4, "name4", "value4")
                        .record(5, "name5", "value5")
                        .record(6, "name6", "value6")
                        .build();
    }

    @Test
    public void 検索結果が0件の場合は空のオブジェクトが返される() throws Exception {
        // setup
        SearchResult searchResult =
                table(TABLE_ALIAS_FOO)
                        .record(1, "name1", "value1")
                        .record(2, "name2", "value2")
                        .record(3, "name3", "value3")
                        .table(TABLE_ALIAS_BAR)
                        .build();

        // exercise
        ColumnValues columnValues = searchResult.getColumnValues(TABLE_ALIAS_BAR, NAME);

        // verify
        assertThat(columnValues.getValues()).isEmpty();
    }

    private static SearchResultBuilder table(TableAlias tableAlias) {
        return new SearchResultBuilder().table(tableAlias);
    }
}