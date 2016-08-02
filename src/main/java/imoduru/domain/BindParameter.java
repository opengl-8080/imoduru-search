package imoduru.domain;

import lombok.Value;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * 埋め込みパラメータ.
 */
@Value
public class BindParameter {
    Object value;

    public int setParameter(PreparedStatement ps, int index) {
        try {
            if (this.value instanceof Iterable) {
                Iterable<?> iterable = (Iterable<?>) this.value;

                for (Object v : iterable) {
                    ps.setObject(index++, v);
                }
            } else if (this.value instanceof LocalDate) {
                LocalDate localDate = (LocalDate) this.value;
                LocalDateTime localDateTime = localDate.atTime(LocalTime.of(0, 0));
                Instant instant = localDateTime.toInstant(ZoneOffset.ofHours(9));
                Date utilDate = Date.from(instant);
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                ps.setDate(index++, sqlDate);
            } else {
                ps.setObject(index++, this.value);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return index;
    }
}
