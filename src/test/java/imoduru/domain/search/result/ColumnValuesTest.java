package imoduru.domain.search.result;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class ColumnValuesTest {

    @Test
    public void 各カラム値を変換した結果を新しい不変リストで取得できる() throws Exception {
        // setup
        ColumnValues values = new ColumnValues(Lists.immutable.of(
                new ColumnValue(1),
                new ColumnValue(2),
                new ColumnValue(3)
        ));

        // exercise
        ImmutableList<String> stringList = values.map(value -> String.valueOf(value));

        // verify
        assertThat(stringList).containsExactly("1", "2", "3");
    }
}