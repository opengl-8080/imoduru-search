package imoduru.domain.search.value;

import imoduru.domain.search.Context;
import imoduru.domain.search.expression.BindParameter;

/**
 * 検索値.
 */
public interface SearchValue {

    /**
     * この検索値が持つ値をもとに、埋め込みパラメータを生成する.
     * @param context コンテキスト
     * @return 埋め込みパラメータ
     */
    BindParameter createBindParameter(Context context);
}
