package com.pamihnenkov.supplier.dadata.domain.organization;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.LocalDate;

import com.pamihnenkov.supplier.dadata.json.LocalDateDeserializer;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class State {

    @JsonAlias("actuality_date")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    LocalDate actualityDate;
    @JsonAlias("registration_date")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    LocalDate registrationDate;
    @JsonAlias("liquidation_date")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    LocalDate liquidationDate;
    String code;
    OrganizationStatus status;
}