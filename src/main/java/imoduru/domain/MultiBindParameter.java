package imoduru.domain;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.experimental.NonFinal;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;

import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.Optional;

/**
 * 複数埋め込みパラメータ.
 */
@Value
@EqualsAndHashCode(exclude = {"sizeCache", "converters"})
@RequiredArgsConstructor
public class MultiBindParameter implements BindParameter {

    Iterable<BindParameter> iterable;
    @NonFinal
    Integer sizeCache;
    @NonFinal
    MutableList<Converter> converters = Lists.mutable.of();

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
            this.converters.each(singleBindParameter::pushConverter);

            index = singleBindParameter.setParameter(ps, index);

            this.converters.each(c -> singleBindParameter.popConverter());
        }

        return index;
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
