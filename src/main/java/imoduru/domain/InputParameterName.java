package imoduru.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
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
