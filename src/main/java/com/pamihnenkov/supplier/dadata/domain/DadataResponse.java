package com.pamihnenkov.supplier.dadata.domain;

import java.util.List;
import lombok.Value;

@Value
public class DadataResponse<T> {

    private List<Suggestion<T>> suggestions;

}