package imoduru.domain;

import mockit.Mocked;
import mockit.Verifications;
import org.assertj.core.api.OffsetTimeAssert;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Date;

import static org.assertj.core.api.Assertions.*;

public class BindParameterTest {

    @Mocked
    private PreparedStatement ps;

    @Test
    public void 内部に持つ値をPreparedStatementにセットする() throws Exception {
        // setup
        BindParameter parameter = new BindParameter("123");

        // exercise
        parameter.setParameter(ps, 1);

        // verify
        new Verifications() {{
            ps.setObject(1, "123"); times = 1;
        }};
    }

    @Test
    public void 内部値がLocalDateの場合_Dateに変換されてからセットされる() throws Exception {
        // setup
        LocalDate localDate = LocalDate.parse("2016-08-02");
        BindParameter parameter = new BindParameter(localDate);

        // exercise
        parameter.setParameter(ps, 1);

        // verify
        LocalDateTime localDateTime = localDate.atTime(LocalTime.of(0, 0));
        Instant instant = localDateTime.toInstant(ZoneOffset.ofHours(9));
        Date utilDate = Date.from(instant);
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        new Verifications() {{
            ps.setDate(1, sqlDate); times = 1;
        }};
    }

    @Test
    public void Iterableを実装した値がセットされている場合_ループしながら値がセットされること() throws Exception {
        // setup
        Iterable<String> iterable = Arrays.asList("a", "b", "c");
        BindParameter parameter = new BindParameter(iterable);

        // exercise
        parameter.setParameter(ps, 2);

        // verify
        new Verifications() {{
            ps.setObject(2, "a"); times = 1;
            ps.setObject(3, "b"); times = 1;
            ps.setObject(4, "c"); times = 1;
        }};
    }

    @Test
    public void 戻り値の値は_引数で渡したインデックスに内部でパラメータをセットした回数分加算した値が返されること_値がIterableでない場合() throws Exception {
        // setup
        BindParameter parameter = new BindParameter("123");

        // exercise
        int actual = parameter.setParameter(ps, 1);

        // verify
        assertThat(actual).isEqualTo(2);
    }

    @Test
    public void 戻り値の値は_引数で渡したインデックスに内部でパラメータをセットした回数分加算した値が返されること_値がIterableの場合() throws Exception {
        // setup
        Iterable<String> iterable = Arrays.asList("a", "b", "c");
        BindParameter parameter = new BindParameter(iterable);

        // exercise
        int actual = parameter.setParameter(ps, 1);

        // verify
        assertThat(actual).isEqualTo(4);
    }
}