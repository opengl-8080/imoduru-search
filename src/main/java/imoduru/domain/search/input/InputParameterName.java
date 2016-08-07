package imoduru.domain.search.input;

import imoduru.domain.search.SearchDefinitionName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;

/**
 * 入力パラメータ名.
 */
@Value
public class InputParameterName {
    @Getter(AccessLevel.PACKAGE)
    SearchDefinitionName searchDefinitionName;
    @Getter(AccessLevel.PACKAGE)
    String name;
}
