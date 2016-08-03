package imoduru.domain;

/**
 * 検索値.
 */
public interface SearchValue {

    BindParameter createBindParameter(InputData inputData);
}
