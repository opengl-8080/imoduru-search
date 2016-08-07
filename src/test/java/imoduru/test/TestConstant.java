package imoduru.test;

import imoduru.domain.BindParameter;
import imoduru.domain.Column;
import imoduru.domain.ColumnSearchCondition;
import imoduru.domain.ConditionExpression;
import imoduru.domain.ConditionExpressions;
import imoduru.domain.EqualTo;
import imoduru.domain.FixedValue;
import imoduru.domain.InputParameterDefinition;
import imoduru.domain.InputParameterName;
import imoduru.domain.MultiBindParameter;
import imoduru.domain.SearchDefinitionName;
import imoduru.domain.SingleBindParameter;
import imoduru.domain.SortCondition;
import imoduru.domain.SortConditions;
import imoduru.domain.SortDirection;
import imoduru.domain.SqlStatement;
import imoduru.domain.Table;
import imoduru.domain.TableAlias;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.utility.ArrayIterate;

public class TestConstant {
    public static final SearchDefinitionName SEARCH_DEFINITION_NAME_A = new SearchDefinitionName("A");

    public static final Table TABLE_FOO = new Table("FOO");
    public static final Table TABLE_BAR = new Table("BAR");
    public static final TableAlias TABLE_ALIAS_FOO = new TableAlias(TABLE_FOO, "foo");
    public static final TableAlias TABLE_ALIAS_BAR = new TableAlias(TABLE_BAR, "bar");

    public static final Column ID = new Column("ID");
    public static final Column NAME = new Column("NAME");
    public static final Column VALUE = new Column("VALUE");

    public static final InputParameterName INPUT_PARAMETER_NAME_1 = new InputParameterName(SEARCH_DEFINITION_NAME_A, "1");
    public static final InputParameterName INPUT_PARAMETER_NAME_2 = new InputParameterName(SEARCH_DEFINITION_NAME_A, "2");
    public static final InputParameterName INPUT_PARAMETER_NAME_3 = new InputParameterName(SEARCH_DEFINITION_NAME_A, "3");

    public static InputParameterDefinition createInputParameterDefinition(InputParameterName... names) {
        return new InputParameterDefinition(Lists.immutable.of(names));
    }

    public static ColumnSearchCondition createColumnSearchCondition(String columnName, Object value) {
        Column column = new Column(columnName);
        FixedValue fixedValue = new FixedValue(value);
        return new EqualTo(column, fixedValue);
    }

    public static ConditionExpressions createConditionExpressions(ConditionExpression... conditionExpressions) {
        return new ConditionExpressions(Lists.immutable.of(conditionExpressions));
    }

    public static ConditionExpression createConditionExpression(String sql, Object value) {
        SqlStatement sqlStatement = new SqlStatement(sql);
        BindParameter bindParameter = new SingleBindParameter(value);
        return new ConditionExpression(sqlStatement, bindParameter);
    }

    public static ConditionExpression createConditionExpression(String sql, Object... values) {
        SqlStatement sqlStatement = new SqlStatement(sql);
        MutableList<BindParameter> parameters = ArrayIterate.collect(values, SingleBindParameter::new);
        BindParameter bindParameter = new MultiBindParameter(parameters);
        return new ConditionExpression(sqlStatement, bindParameter);
    }

    public static SortConditions createSortConditions(SortCondition... sortConditions) {
        return new SortConditions(Lists.immutable.of(sortConditions));
    }

    public static SortCondition createSortCondition(String column, SortDirection direction) {
        return new SortCondition(new Column(column), direction);
    }
}
