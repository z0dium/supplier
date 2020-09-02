package com.pamihnenkov.supplier.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class of unique good to purchase. Unique line of nomenclature
 */

@Data
@Entity
@Builder
@Table(name = "items")
public class Item extends BaseEntity {

    private String name; //Subject. 1-2 words
    private String serialNumber; //Any combination to identify the DIN, ISO, model, and so on...
    private String description; //Any additional information
    private String brand;
    private String category;
    private String urlOfExample;
    private String image;

}
