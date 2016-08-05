package imoduru.domain;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;

/**
 * テーブル検索条件.
 */
public class TableSearchCondition implements SearchDefinitionDetail {

    private final Table table;
    private final TableAlias tableAlias;
    private SortConditions sortConditions = new SortConditions(Lists.immutable.empty());
    private MutableList<ColumnSearchCondition> columnSearchConditions = Lists.mutable.empty();

    public TableSearchCondition(Table table, TableAlias tableAlias) {
        this.table = table;
        this.tableAlias = tableAlias;
    }

    @Override
    public ConditionExpressions createConditionExpressions(InputData inputData) {
        ImmutableList<ConditionExpression> expressions =
                this.columnSearchConditions.collect(it -> it.createConditionExpression(inputData)).toImmutable();

        return new ConditionExpressions(expressions);
    }

    public void add(ColumnSearchCondition columnSearchCondition) {
        this.columnSearchConditions.add(columnSearchCondition);
    }
}
