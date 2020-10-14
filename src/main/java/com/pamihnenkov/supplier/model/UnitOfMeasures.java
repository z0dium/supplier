package com.pamihnenkov.supplier.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * For properly working its forbidden to edit this enum (except the adding new entries) on the production, may
 * cause issues with ordinal indexes.
 */

public enum UnitOfMeasures {
    //L ("л"),
    //M3  ("м³"),
    //M2  ("м²"),
    //M ("м"),
    //CM ("см"),
    //TN ("тн"),
    //KILOGRAM  ("кг"),
    //GRAM ("гр"),
    //PACK  ("уп"),
    //PCS ("шт"),
    //SET ("пар");

    л ("л"),
    м3  ("м³"),
    м2  ("м²"),
    м ("м"),
    см ("см"),
    тн("тн"),
    кг  ("кг"),
    гр ("гр"),
    уп  ("уп"),
    шт ("шт"),
    пар ("пар");

    private final String title;

    UnitOfMeasures(String title){
        this.title=title;
    }

    public String getTitle() {
        return title;
    }

    public List<String> toList(){
        return Stream.of(UnitOfMeasures.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
