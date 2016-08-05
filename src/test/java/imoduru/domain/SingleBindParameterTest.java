package imoduru.domain;

import mockit.Mocked;
import mockit.Verifications;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class SingleBindParameterTest {

    @Mocked
    private PreparedStatement ps;

    @Test
    public void 内部に持つ値をPreparedStatementにセットする() throws Exception {
        // setup
        SingleBindParameter parameter = new SingleBindParameter("123");

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
        SingleBindParameter parameter = new SingleBindParameter(localDate);

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
    public void 戻り値の値は_引数で渡したインデックスに内部でパラメータをセットした回数分加算した値が返されること_値がIterableでない場合() throws Exception {
        // setup
        SingleBindParameter parameter = new SingleBindParameter("123");

        // exercise
        int actual = parameter.setParameter(ps, 1);

        // verify
        assertThat(actual).isEqualTo(2);
    }

    @Test
    public void pushした順序でコンバーターが適用されること() throws Exception {
        // setup
        SingleBindParameter parameter = new SingleBindParameter("123");

        parameter.pushConverter((v) -> "[" + v + "]");
        parameter.pushConverter((v) -> "{" + v + "}");

        // exercise
        parameter.setParameter(ps, 1);

        // verify
        new Verifications() {{
            ps.setObject(1, "{[123]}"); times = 1;
        }};
    }

    @Test
    public void popで最後にpushしたコンバーターを抜き取れる() throws Exception {
        // setup
        SingleBindParameter parameter = new SingleBindParameter("123");

        parameter.pushConverter((v) -> "[" + v + "]");
        Converter expected = (v) -> "{" + v + "}";
        parameter.pushConverter(expected);

        // exercise
        Optional<Converter> actual = parameter.popConverter();

        // verify
        assertThat(actual.get()).isSameAs(expected);
    }

    @Test
    public void popで抜き取ったコンバーターは適用されなくなる() throws Exception {
        // setup
        SingleBindParameter parameter = new SingleBindParameter("123");

        parameter.pushConverter((v) -> "[" + v + "]");
        parameter.pushConverter((v) -> "{" + v + "}");

        // exercise
        parameter.popConverter();
        parameter.setParameter(ps, 1);

        // verify
        new Verifications() {{
            ps.setObject(1, "[123]"); times = 1;
        }};
    }
}