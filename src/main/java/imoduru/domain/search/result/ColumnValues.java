package imoduru.domain.search.result;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;
import org.eclipse.collections.api.list.ImmutableList;

import java.util.function.Function;


/**
 * カラム値一覧.
 */
@Value
public class ColumnValues {
    @Getter(AccessLevel.PACKAGE)
    ImmutableList<ColumnValue> values;

    /**
     * この一覧が持つ各カラム値の生の値を変換して、新しい不変リストにして返す.
     * @param converter カラム値の変換処理
     * @param <T> 変換後の型
     * @return 変換後の新しい不変リスト
     */
    public <T> ImmutableList<T> map(Function<Object, T> converter) {
        return this.values.collect(columnValue -> converter.apply(columnValue.getValue()));
    }
}
