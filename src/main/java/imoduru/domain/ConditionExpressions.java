package imoduru.domain;

import lombok.Value;
import org.eclipse.collections.api.list.ImmutableList;

import java.sql.PreparedStatement;

/**
 * 条件式一覧.
 */
@Value
public class ConditionExpressions {
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
}
