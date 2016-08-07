package imoduru.domain.search.condition;

import imoduru.domain.SearchResultCollector;
import imoduru.domain.search.Context;
import imoduru.domain.search.expression.ConditionExpressions;
import imoduru.domain.search.sort.SortConditions;
import imoduru.domain.search.sort.SortDirection;
import mockit.Mocked;
import mockit.Verifications;
import org.junit.Test;

import static imoduru.test.TestConstant.*;

public class TableSearchConditionTest {

    @Mocked
    private SearchResultCollector collector;

    @Test
    public void 自身が持つ検索のための条件値を使って_コレクターに検索の指示を出す() throws Exception {
        // setup
        TableSearchCondition tableSearchCondition = new TableSearchCondition(TABLE_ALIAS_FOO);

        tableSearchCondition.add(createColumnSearchCondition("BAR", "a"));
        tableSearchCondition.add(createColumnSearchCondition("FIZZ", "b"));

        SortConditions sortConditions = createSortConditions(
            createSortCondition("BAR", SortDirection.ASC),
            createSortCondition("FIZZ", SortDirection.DESC)
        );

        tableSearchCondition.setSortConditions(sortConditions);

        // exercise
        tableSearchCondition.search(collector, new Context());

        // verify
        ConditionExpressions expressions = createConditionExpressions(
            createConditionExpression("(BAR = ?)", "a"),
            createConditionExpression("(FIZZ = ?)", "b")
        );

        new Verifications() {{
            collector.search(TABLE_ALIAS_FOO, expressions, sortConditions); times = 1;
        }};
    }
}