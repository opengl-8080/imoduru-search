package imoduru.domain.search.input;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;
import org.eclipse.collections.api.list.ImmutableList;

/**
 * 入力パラメータ定義.
 */
@Value
public class InputParameterDefinition {
    @Getter(AccessLevel.PACKAGE)
    ImmutableList<InputParameterName> names;
}
