package imoduru.domain.search;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;

/**
 * 検索定義名称.
 */
@Value
public class SearchDefinitionName {
    @Getter(AccessLevel.PACKAGE)
    String name;
}
