package imoduru.domain.search.value;

import imoduru.domain.search.Context;
import imoduru.domain.search.expression.BindParameter;
import imoduru.domain.search.expression.MultiBindParameter;
import imoduru.domain.search.expression.SingleBindParameter;
import imoduru.domain.search.result.ColumnValues;
import imoduru.domain.table.Column;
import imoduru.domain.table.TableAlias;
import lombok.AccessLevel;
import lombok.Getter;
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
    public BindParameter createBindParameter(Context context) {
        ColumnValues columnValues = context.getColumnValues(this.tableAlias, this.column);

        ImmutableList<BindParameter> bindParameters =
                columnValues
                .map(SingleBindParameter::new);

        return new MultiBindParameter(bindParameters);
    }
}
