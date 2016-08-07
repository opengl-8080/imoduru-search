package imoduru.domain.search.condition;

import imoduru.domain.search.Context;
import imoduru.domain.search.expression.ConditionExpression;
import imoduru.domain.table.Column;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * カラム検索条件.
 */
@ToString
@EqualsAndHashCode
public abstract class ColumnSearchCondition {
    /**
     * 比較対象のカラム.
     */
    protected final Column column;

    /**
     * 比較対象のカラムを指定して新しいインスタンスを生成する.
     * @param column カラム
     */
    protected ColumnSearchCondition(Column column) {
        this.column = column;
    }

    /**
     * 条件式を生成する.
     * @param context コンテキスト
     * @return 条件式
     */
    public abstract ConditionExpression createConditionExpression(Context context);
}
