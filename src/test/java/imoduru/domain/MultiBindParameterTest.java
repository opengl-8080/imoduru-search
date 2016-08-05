package imoduru.domain;

import mockit.Mocked;
import mockit.Verifications;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MultiBindParameterTest {

    @Mocked @SuppressWarnings("unused")
    private PreparedStatement ps;

    private SingleBindParameter paramA = new SingleBindParameter("a");
    private SingleBindParameter paramB = new SingleBindParameter("b");
    private SingleBindParameter paramC = new SingleBindParameter("c");

    @Test
    public void popしたコンバーターは適用されなくなる() throws Exception {
        // setup
        MultiBindParameter parameter = new MultiBindParameter(Arrays.asList(paramA, paramB));
        parameter.pushConverter(v -> "(" + v + ")");
        parameter.pushConverter(v -> "[" + v + "]");
        parameter.popConverter();

        // exercise
        parameter.setParameter(ps, 1);

        // verify
        new Verifications() {{
            ps.setObject(1, "(a)"); times = 1;
            ps.setObject(2, "(b)"); times = 1;
        }};
    }

    @Test
    public void popで最後に追加したコンバーターが取得できる() throws Exception {
        // setup
        MultiBindParameter parameter = new MultiBindParameter(Collections.emptyList());
        parameter.pushConverter(v -> v);
        Converter converter = v -> "[" + v + "]";
        parameter.pushConverter(converter);

        // exercise
        Optional<Converter> actual = parameter.popConverter();

        // verify
        assertThat(actual.get()).isSameAs(converter);
    }

    @Test
    public void コンバーターをセットした場合_各埋め込みパラメータに実行前にコンバーターがセットされ_実行後に削除される() throws Exception {
        // setup
        Converter converter = v -> "{" + v + "}";
        paramB.pushConverter(converter);

        Iterable<BindParameter> iterable = Arrays.asList(paramA, paramB);
        MultiBindParameter parameter = new MultiBindParameter(iterable);
        parameter.pushConverter(v -> "[" + v + "]");

        // exercise
        parameter.setParameter(ps, 2);

        // verify
        new Verifications() {{
            ps.setObject(2, "[a]"); times = 1;
            ps.setObject(3, "[{b}]"); times = 1;
        }};

        Optional<Converter> actualConverter = paramB.popConverter();
        assertThat(actualConverter.get()).isSameAs(converter);
    }

    @Test
    public void Iterableを実装した値がセットされている場合_ループしながら値がセットされること() throws Exception {
        // setup
        Iterable<BindParameter> iterable = Arrays.asList(paramA, paramB, paramC);
        MultiBindParameter parameter = new MultiBindParameter(iterable);

        // exercise
        parameter.setParameter(ps, 2);

        // verify
        new Verifications() {{
            paramA.setParameter(ps, 2); times = 1;
            paramB.setParameter(ps, 3); times = 1;
            paramC.setParameter(ps, 4); times = 1;
        }};
    }

    @Test
    public void 戻り値の値は_引数で渡したインデックスに内部でパラメータをセットした回数分加算した値が返されること_値がIterableの場合() throws Exception {
        // setup
        Iterable<BindParameter> iterable = Arrays.asList(paramA, paramB, paramC);
        MultiBindParameter parameter = new MultiBindParameter(iterable);

        // exercise
        int actual = parameter.setParameter(ps, 1);

        // verify
        assertThat(actual).isEqualTo(4);
    }
}