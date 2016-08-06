package imoduru.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.Lists;

import java.sql.PreparedStatement;

/**
 * 条件式一覧.
 */
@Value
public class ConditionExpressions {
    @Getter(AccessLevel.PACKAGE)
    ImmutableList<ConditionExpression> expressions;

    /**
     * 各条件式が持つ SQL 文を AND で連結した生の文字列を取得する.
     * @return SQL 文
     */
    public String getRawSql() {
        return this.expressions
                .collect(exp -> exp.getSqlStatement().getValue())
                .makeString(" AND ");
    }

    /**
     * 各条件式にパラメータの設定を指示する.
     * @param ps {@link PreparedStatement}
     */
    public void setParametersInto(PreparedStatement ps) {
        int index = 1;

        for (ConditionExpression expression : this.expressions) {
            index = expression.getBindParameter().setParameter(ps, index);
        }
    }

    /**
     * 指定した条件式一覧を結合した新しい条件式一覧を生成する.
     * @param other 結合する条件式一覧
     * @return 結合された新しい条件式一覧
     */
    public ConditionExpressions addAll(ConditionExpressions other) {
        return new ConditionExpressions(this.expressions.newWithAll(other.expressions));
    }
}
