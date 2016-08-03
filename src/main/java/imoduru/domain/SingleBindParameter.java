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
 * 単一埋め込みパラメータ.
 */
@Value
public class SingleBindParameter implements BindParameter {

    Object value;

    @Override
    public int setParameter(PreparedStatement ps, int index) {
        try {
            if (this.value instanceof LocalDate) {
                LocalDate localDate = (LocalDate) this.value;
                LocalDateTime localDateTime = localDate.atTime(LocalTime.of(0, 0));
                Instant instant = localDateTime.toInstant(ZoneOffset.ofHours(9));
                Date utilDate = Date.from(instant);
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                ps.setDate(index++, sqlDate);
            } else {
                ps.setObject(index++, this.value);
            }

            return index;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
