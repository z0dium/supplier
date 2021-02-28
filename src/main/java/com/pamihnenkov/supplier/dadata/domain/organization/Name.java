package com.pamihnenkov.supplier.dadata.domain.organization;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Name {

    @JsonAlias("full_with_opf")
    String fullWithOpf;
    @JsonAlias("short_with_opf")
    String shortWithOpf;
    String latin;
    /**
     * @deprecated Will be deleted at 2021. Use {@link #fullWithOpf} instead
     */
    @JsonAlias("full")
    @Deprecated
    String fullName;
    /**
     * @deprecated Will be deleted at 2021. Use {@link #shortWithOpf} instead
     */
    @JsonAlias("short")
    @Deprecated
    String shortName;
}