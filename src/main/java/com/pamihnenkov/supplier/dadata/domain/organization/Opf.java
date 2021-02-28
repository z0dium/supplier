package com.pamihnenkov.supplier.dadata.domain.organization;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Opf {

    String type;
    String code;
    @JsonAlias("full")
    String fullName;
    @JsonAlias("short")
    String shortName;
}