package com.pamihnenkov.supplier.dadata.exception;

import lombok.Value;

@Value
public class ErrorDetails {

    private String family;
    private String reason;
    private String message;
}