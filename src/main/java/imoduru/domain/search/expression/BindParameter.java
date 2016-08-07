package imoduru.domain.search.expression;

import imoduru.util.Converter;

import java.sql.PreparedStatement;
import java.util.Optional;

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

    /**
     * 値を任意に変換するコンバーターを追加する.
     * <p>
     * 変換は追加した順番で実行される.
     *
     * @param converter コンバーター
     */
    void pushConverter(Converter converter);

    /**
     * 最後に追加したコンバーターを取り出し、削除する.
     * <p>
     * 登録されているコンバーターが存在しない状態でこのメソッドを実行した場合、
     * 空の {@link Optional} が返されます.
     *
     * @return 最後に適用したコンバーター.
     */
    Optional<Converter> popConverter();
}
