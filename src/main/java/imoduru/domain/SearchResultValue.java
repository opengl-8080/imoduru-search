package imoduru.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.Value;
import org.eclipse.collections.api.list.ImmutableList;

/**
 * 検索結果値.
 */
@Value
public class SearchResultValue implements MultiSearchValue {
    @Getter(AccessLevel.PACKAGE)
    TableAlias tableAlias;
    @Getter(AccessLevel.PACKAGE)
    Column column;

    @Override
    public BindParameter createBindParameter(InputData inputData) {
        ColumnValues columnValues = inputData.getColumnValues(this.tableAlias, this.column);

        ImmutableList<BindParameter> bindParameters =
                columnValues
                .getValues()
                .collect(columnValue -> new SingleBindParameter(columnValue.getValue()));

        return new MultiBindParameter(bindParameters);
    }
}
