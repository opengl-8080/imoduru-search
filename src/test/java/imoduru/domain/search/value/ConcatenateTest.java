package imoduru.domain.search.value;

import imoduru.domain.search.Context;
import imoduru.domain.search.expression.BindParameter;
import imoduru.domain.search.value.Concatenate;
import imoduru.domain.search.value.FixedValue;
import mockit.Mocked;
import mockit.Verifications;
import org.junit.Test;

import java.sql.PreparedStatement;

public class ConcatenateTest {

    @Mocked
    private PreparedStatement ps;

    @Test
    public void name() throws Exception {
        // setup
        FixedValue fixedValue = new FixedValue("aaa");
        Concatenate concatenate = new Concatenate("Prefix", fixedValue);

        // exercise
        BindParameter bindParameter = concatenate.createBindParameter(new Context());

        // verify
        bindParameter.setParameter(ps, 1);

        new Verifications() {{
            ps.setObject(1, "Prefix" + "aaa"); times = 1;
        }};
    }
}