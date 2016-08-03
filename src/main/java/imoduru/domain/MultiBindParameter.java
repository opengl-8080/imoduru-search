package imoduru.domain;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.experimental.NonFinal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;

/**
 * 複数埋め込みパラメータ.
 */
@Value
@RequiredArgsConstructor
public class MultiBindParameter implements BindParameter {

    Iterable<BindParameter> iterable;
    @NonFinal
    Integer sizeCache;

    @Override
    public int size() {
        if (this.sizeCache == null) {
            int cnt = 0;
            Iterator<?> iterator = this.iterable.iterator();
            while (iterator.hasNext()) {
                iterator.next();
                cnt++;
            }
            this.sizeCache = cnt;
        }
        return this.sizeCache;
    }

    @Override
    public int setParameter(PreparedStatement ps, int index) {
        for (BindParameter singleBindParameter : this.iterable) {
            index = singleBindParameter.setParameter(ps, index);
        }

        return index;
    }
}
