package com.pamihnenkov.supplier.dadata.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @see <a href="https://confluence.hflabs.ru/pages/viewpage.action?pageId=204669126">Dadata organization object</a>
 */


@JsonInclude(JsonInclude.Include.NON_NULL)
public class DadataOrganization {

    @JsonProperty("value")
    String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}


