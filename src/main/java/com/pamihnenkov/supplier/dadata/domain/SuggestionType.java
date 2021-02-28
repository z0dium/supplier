package com.pamihnenkov.supplier.dadata.domain;

import org.springframework.core.ParameterizedTypeReference;

public interface SuggestionType<T> {

    ParameterizedTypeReference<DadataResponse<T>> getResponseClass();

    String getSuggestOperationPrefix();

    default String getFindByIdOperationPrefix() {
        return getSuggestOperationPrefix();
    }
}