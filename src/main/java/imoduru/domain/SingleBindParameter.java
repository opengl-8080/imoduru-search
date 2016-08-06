package imoduru.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.experimental.NonFinal;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

/**
 * 単一埋め込みパラメータ.
 */
@Value
@EqualsAndHashCode(exclude = "converters")
@RequiredArgsConstructor
public class SingleBindParameter implements BindParameter {

    @Getter(AccessLevel.PACKAGE)
    Object value;

    @NonFinal
    @Getter(AccessLevel.PACKAGE)
    MutableList<Converter> converters = Lists.mutable.of();

    @Override
    public int size() {
        return 1;
    }

    @Override
    public int setParameter(PreparedStatement ps, int index) {
        try {
            Object value = this.value;

            for (Converter converter : this.converters) {
                value = converter.convert(value);
            }

            if (value instanceof LocalDate) {
                LocalDate localDate = (LocalDate) value;
                LocalDateTime localDateTime = localDate.atTime(LocalTime.of(0, 0));
                Instant instant = localDateTime.toInstant(ZoneOffset.ofHours(9));
                Date utilDate = Date.from(instant);
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                ps.setDate(index++, sqlDate);
            } else {
                ps.setObject(index++, value);
            }

            return index;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void pushConverter(Converter converter) {
        this.converters.add(converter);
    }

    @Override
    public Optional<Converter> popConverter() {
        Converter last = this.converters.getLast();
        if (last == null) {
            return Optional.empty();
        }

        this.converters.remove(last);
        return Optional.of(last);
    }
}
