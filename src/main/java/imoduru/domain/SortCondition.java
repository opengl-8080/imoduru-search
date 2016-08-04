package imoduru.domain;

import lombok.Value;

/**
 * ソート条件.
 */
@Value
public class SortCondition {
    Column column;
    SortDirection sortDirection;
}
