package com.pamihnenkov.supplier.dadata;

import com.pamihnenkov.supplier.dadata.domain.DadataOrganization;
import com.pamihnenkov.supplier.dadata.domain.SuggestionType;
import com.pamihnenkov.supplier.dadata.domain.organization.OrganizationSuggestion;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SuggestionTypes {

    public static final SuggestionType<DadataOrganization> ORGANIZATION = new OrganizationSuggestion();
}