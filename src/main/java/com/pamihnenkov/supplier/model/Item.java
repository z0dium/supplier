package com.pamihnenkov.supplier.model;

//Class of unique good to purchase. Unique line of nomenclature

public class Item extends BaseEntity {

    private String name; //Subject. 1-2 words
    private String serialNumber; //Any combination to identify the DIN, ISO, model, and so on...
    private String description; //Any additional information
    private String brand;
    private String group;
    private String urlOfExample;
    private String image;

}
