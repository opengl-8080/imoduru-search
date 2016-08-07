package imoduru.domain.search.sort;

import imoduru.test.TestConstant;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class SortConditionsTest {

    @Test
    public void 各ソート条件をカンマでつないだ生のSQLが取得できる() throws Exception {
        // setup
        ImmutableList<SortCondition> conditions = Lists.immutable.of(
            TestConstant.createSortCondition("FOO", SortDirection.ASC),
            TestConstant.createSortCondition("BAR", SortDirection.DESC)
        );

        SortConditions sortConditions = new SortConditions(conditions);

        // exercise
        String rawSql = sortConditions.getRawSql();

        // verify
        assertThat(rawSql).isEqualTo("FOO ASC, BAR DESC");
    }
}