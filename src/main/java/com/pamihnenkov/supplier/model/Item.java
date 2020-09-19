package com.pamihnenkov.supplier.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class of unique good to purchase. Unique line of nomenclature
 */

@Data
@Entity
@NoArgsConstructor
@Table(name = "items")
public class Item extends BaseEntity {

    private String name; //Subject. 1-2 words
    private String model; //Any combination to identify the DIN, ISO, serial number, and so on...
    private String brand;
    private String category;
    private String urlOfExample;
    private String image;

}
