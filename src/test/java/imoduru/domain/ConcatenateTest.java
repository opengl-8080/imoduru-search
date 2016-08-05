package imoduru.domain;

import mockit.Mocked;
import mockit.Verifications;
import org.junit.Test;

import java.sql.PreparedStatement;

import static org.assertj.core.api.Assertions.*;

public class ConcatenateTest {

    @Mocked
    private PreparedStatement ps;

    @Test
    public void name() throws Exception {
        // setup
        FixedValue fixedValue = new FixedValue("aaa");
        Concatenate concatenate = new Concatenate("Prefix", fixedValue);

        // exercise
        BindParameter bindParameter = concatenate.createBindParameter(new InputData());

        // verify
        bindParameter.setParameter(ps, 1);

        new Verifications() {{
            ps.setObject(1, "Prefix" + "aaa"); times = 1;
        }};
    }
}