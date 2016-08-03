package imoduru.domain;

import java.sql.PreparedStatement;

/**
 * 埋め込みパラメータ.
 */
public interface BindParameter {

    /**
     * この埋め込みパラメータが持つ値の数を返す.
     * @return パラメータの数
     */
    int size();

    /**
     * 指定した {@link PreparedStatement} にこのパラメータが持つ値をセットする.
     * @param ps {@link PreparedStatement}
     * @param index 値を埋め込むインデックス値
     * @return 値を埋め込んだ後の次のインデックス値
     */
    int setParameter(PreparedStatement ps, int index);
}
