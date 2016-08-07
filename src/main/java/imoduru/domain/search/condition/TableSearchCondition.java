package imoduru.domain.search.condition;

import imoduru.domain.search.Context;
import imoduru.domain.search.SearchDefinitionDetail;
import imoduru.domain.SearchResultCollector;
import imoduru.domain.search.expression.ConditionExpression;
import imoduru.domain.search.expression.ConditionExpressions;
import imoduru.domain.search.sort.SortConditions;
import imoduru.domain.table.TableAlias;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;

/**
 * テーブル検索条件.
 */
public class TableSearchCondition implements SearchDefinitionDetail {

    private final TableAlias tableAlias;
    private SortConditions sortConditions = new SortConditions(Lists.immutable.empty());
    private MutableList<ColumnSearchCondition> columnSearchConditions = Lists.mutable.empty();

    public TableSearchCondition(TableAlias tableAlias) {
        this.tableAlias = tableAlias;
    }

    @Override
    public void search(SearchResultCollector collector, Context context) {
        ImmutableList<ConditionExpression> expressions =
                this.columnSearchConditions.collect(it -> it.createConditionExpression(context)).toImmutable();

        collector.search(this.tableAlias, new ConditionExpressions(expressions), this.sortConditions);
    }

    public void add(ColumnSearchCondition columnSearchCondition) {
        this.columnSearchConditions.add(columnSearchCondition);
    }

    public void setSortConditions(SortConditions sortConditions) {
        this.sortConditions = sortConditions;
    }
}
