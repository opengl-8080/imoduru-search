package imoduru.domain;

import lombok.Value;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 複数埋め込みパラメータ.
 */
@Value
public class MultiBindParameter implements BindParameter {

    Iterable<?> iterable;

    @Override
    public int setParameter(PreparedStatement ps, int index) {
        try {
            for (Object value : this.iterable) {
                ps.setObject(index++, value);
            }

            return index;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
