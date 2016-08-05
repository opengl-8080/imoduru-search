package imoduru.domain;

import lombok.Value;
import org.eclipse.collections.api.set.ImmutableSet;

/**
 * 入力パラメータ定義.
 */
@Value
public class InputParameterDefinition {
    ImmutableSet<InputParameterName> names;
}
