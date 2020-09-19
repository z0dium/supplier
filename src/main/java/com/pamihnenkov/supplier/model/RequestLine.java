package com.pamihnenkov.supplier.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "requestLines")
public class RequestLine extends BaseEntity{

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Request request;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Item item;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Uom unitOfMeasure;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Contragent contragent;

    private String description; //Any additional information
    private Integer orderedQuantity;
    private Integer deliveredQuantity;
    private Long expectedTo;
    private Long deliveryDay;
}
