package imoduru.test;

import imoduru.domain.Column;
import imoduru.domain.InputParameterName;
import imoduru.domain.SearchDefinitionName;

public class TestConstant {
    public static final SearchDefinitionName SEARCH_DEFINITION_NAME_A = new SearchDefinitionName("A");

    public static final Column ID = new Column("ID");
    public static final Column NAME = new Column("NAME");
    public static final Column VALUE = new Column("VALUE");

    public static final InputParameterName INPUT_PARAMETER_NAME_1 = new InputParameterName(SEARCH_DEFINITION_NAME_A, "1");
    public static final InputParameterName INPUT_PARAMETER_NAME_2 = new InputParameterName(SEARCH_DEFINITION_NAME_A, "2");
    public static final InputParameterName INPUT_PARAMETER_NAME_3 = new InputParameterName(SEARCH_DEFINITION_NAME_A, "3");

}
