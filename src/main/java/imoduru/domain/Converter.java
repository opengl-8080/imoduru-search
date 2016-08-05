package imoduru.domain;

@FunctionalInterface
public interface Converter {
    Object convert(Object value);
}
