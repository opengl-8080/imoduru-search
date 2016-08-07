package imoduru.domain.search.sort;

import imoduru.domain.table.Column;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class SortConditionTest {

    @Test
    public void 昇順SQL生成() throws Exception {
        // setup
        Column column = new Column("FOO");
        SortCondition sortCondition = new SortCondition(column, SortDirection.ASC);

        // exercise
        String rawSql = sortCondition.getRawSql();

        // verify
        assertThat(rawSql).isEqualTo("FOO ASC");
    }

    @Test
    public void 降順SQL生成() throws Exception {
        // setup
        Column column = new Column("FOO");
        SortCondition sortCondition = new SortCondition(column, SortDirection.DESC);

        // exercise
        String rawSql = sortCondition.getRawSql();

        // verify
        assertThat(rawSql).isEqualTo("FOO DESC");
    }
}