package imoduru.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;

@Value
public class TableAlias {
    @Getter(AccessLevel.PACKAGE)
    Table table;
    @Getter(AccessLevel.PACKAGE)
    String name;
}
