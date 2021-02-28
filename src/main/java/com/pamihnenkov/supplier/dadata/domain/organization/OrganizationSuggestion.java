package com.pamihnenkov.supplier.dadata.domain.organization;

import com.pamihnenkov.supplier.dadata.domain.DadataOrganization;
import com.pamihnenkov.supplier.dadata.domain.DadataResponse;
import com.pamihnenkov.supplier.dadata.domain.SuggestionType;
import org.springframework.core.ParameterizedTypeReference;

public class OrganizationSuggestion implements SuggestionType<DadataOrganization> {

    @Override
    public ParameterizedTypeReference<DadataResponse<DadataOrganization>> getResponseClass() {
        return new ParameterizedTypeReference<DadataResponse<DadataOrganization>>() {};
    }

    @Override
    public String getSuggestOperationPrefix() {
        return "/party";
    }
}