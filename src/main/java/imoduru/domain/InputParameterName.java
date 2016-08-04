package imoduru.domain;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * 入力パラメータ名.
 */
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class InputParameterName {
    private final SearchDefinitionName searchDefinitionName;
    private final String name;
}
