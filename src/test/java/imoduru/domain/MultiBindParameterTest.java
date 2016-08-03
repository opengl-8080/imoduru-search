package imoduru.domain;

import mockit.Mocked;
import mockit.Verifications;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

public class MultiBindParameterTest {

    @Mocked @SuppressWarnings("unused")
    private PreparedStatement ps;

    @Test
    public void Iterableを実装した値がセットされている場合_ループしながら値がセットされること() throws Exception {
        // setup
        SingleBindParameter a = new SingleBindParameter("a");
        SingleBindParameter b = new SingleBindParameter("b");
        SingleBindParameter c = new SingleBindParameter("c");
        Iterable<BindParameter> iterable = Arrays.asList(a, b, c);
        MultiBindParameter parameter = new MultiBindParameter(iterable);

        // exercise
        parameter.setParameter(ps, 2);

        // verify
        new Verifications() {{
            a.setParameter(ps, 2); times = 1;
            b.setParameter(ps, 3); times = 1;
            c.setParameter(ps, 4); times = 1;
        }};
    }

    @Test
    public void 戻り値の値は_引数で渡したインデックスに内部でパラメータをセットした回数分加算した値が返されること_値がIterableの場合() throws Exception {
        // setup
        SingleBindParameter a = new SingleBindParameter("a");
        SingleBindParameter b = new SingleBindParameter("b");
        SingleBindParameter c = new SingleBindParameter("c");
        Iterable<BindParameter> iterable = Arrays.asList(a, b, c);
        MultiBindParameter parameter = new MultiBindParameter(iterable);

        // exercise
        int actual = parameter.setParameter(ps, 1);

        // verify
        assertThat(actual).isEqualTo(4);
    }
}