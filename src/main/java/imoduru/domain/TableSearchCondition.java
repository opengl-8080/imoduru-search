package imoduru.domain;

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
    public void search(SearchResultCollector collector, InputData inputData) {
        ImmutableList<ConditionExpression> expressions =
                this.columnSearchConditions.collect(it -> it.createConditionExpression(inputData)).toImmutable();

        collector.search(this.tableAlias, new ConditionExpressions(expressions), this.sortConditions);
    }

    public void add(ColumnSearchCondition columnSearchCondition) {
        this.columnSearchConditions.add(columnSearchCondition);
    }

    public void setSortConditions(SortConditions sortConditions) {
        this.sortConditions = sortConditions;
    }
}
