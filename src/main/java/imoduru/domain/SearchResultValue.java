package imoduru.domain;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.eclipse.collections.api.list.ImmutableList;

/**
 * 検索結果値.
 */
@RequiredArgsConstructor
@ToString
public class SearchResultValue implements MultiSearchValue {
    private final TableAlias tableAlias;
    private final Column column;

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
