package imoduru.test;

import imoduru.domain.BindParameter;
import imoduru.domain.Column;
import imoduru.domain.ConditionExpression;
import imoduru.domain.InputParameterName;
import imoduru.domain.MultiBindParameter;
import imoduru.domain.SearchDefinitionName;
import imoduru.domain.SingleBindParameter;
import imoduru.domain.SortCondition;
import imoduru.domain.SortDirection;
import imoduru.domain.SqlStatement;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.utility.ArrayIterate;

public class TestConstant {
    public static final SearchDefinitionName SEARCH_DEFINITION_NAME_A = new SearchDefinitionName("A");

    public static final Column ID = new Column("ID");
    public static final Column NAME = new Column("NAME");
    public static final Column VALUE = new Column("VALUE");

    public static final InputParameterName INPUT_PARAMETER_NAME_1 = new InputParameterName(SEARCH_DEFINITION_NAME_A, "1");
    public static final InputParameterName INPUT_PARAMETER_NAME_2 = new InputParameterName(SEARCH_DEFINITION_NAME_A, "2");
    public static final InputParameterName INPUT_PARAMETER_NAME_3 = new InputParameterName(SEARCH_DEFINITION_NAME_A, "3");

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

    public static SortCondition createSortCondition(String column, SortDirection direction) {
        return new SortCondition(new Column(column), direction);
    }
}
