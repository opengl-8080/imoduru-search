package imoduru.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;
import org.eclipse.collections.api.list.ImmutableList;

/**
 * カラム値一覧.
 */
@Value
public class ColumnValues {
    @Getter(AccessLevel.PACKAGE)
    ImmutableList<ColumnValue> values;
}
