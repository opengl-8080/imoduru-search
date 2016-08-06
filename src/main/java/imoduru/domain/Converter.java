package imoduru.domain;

/**
 * 値の変換インターフェース.
 */
@FunctionalInterface
public interface Converter {
    /**
     * 引数で受け取った値を任意の別の値に変換して返す.
     * @param value 変換対象の値
     * @return 変換後の値
     */
    Object convert(Object value);
}
