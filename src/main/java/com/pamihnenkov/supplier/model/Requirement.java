package com.pamihnenkov.supplier.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@Table(name = "requirements")
public class Requirement extends BaseEntity{

//    private Request request;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Item item;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Request request;

    private Integer orderedQuantity;

    private Integer deliveredQuantity;

    private Long expectedTo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Contragent contragent;

    private Long deliveryDay;
}
