package imoduru.domain.search.value;

import imoduru.domain.search.Context;
import imoduru.domain.search.expression.BindParameter;
import imoduru.domain.search.expression.MultiBindParameter;
import imoduru.domain.search.expression.SingleBindParameter;
import imoduru.domain.search.value.SearchResultValue;
import imoduru.test.TableSearchResultBuilder;
import org.junit.Test;

import java.util.Arrays;

import static imoduru.test.TestConstant.*;
import static org.assertj.core.api.Assertions.*;

public class SearchResultValueTest {

    @Test
    public void name() throws Exception {
        // setup
        SearchResultValue searchResultValue = new SearchResultValue(TABLE_ALIAS_FOO, NAME);
        Context context = new Context();

        context.put(TABLE_ALIAS_FOO,
            new TableSearchResultBuilder()
                .record(1, "name1", "value1")
                .record(2, "name2", "value2")
                .record(3, "name3", "value3")
                .build()
        );

        // exercise
        BindParameter bindParameter = searchResultValue.createBindParameter(context);

        // verify
        BindParameter a = new SingleBindParameter("name1");
        BindParameter b = new SingleBindParameter("name2");
        BindParameter c = new SingleBindParameter("name3");
        assertThat(bindParameter).isEqualTo(new MultiBindParameter(Arrays.asList(a, b, c)));
    }
}