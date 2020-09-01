package com.pamihnenkov.supplier.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

public class Requirement extends BaseEntity{

    private Request request;
    private Item item;
    private Integer orderedQuantity;
    private Integer deliveredQuantity;
    private Long expectedTo;
    private Contragent contragent;
    private Long deliveryDay;
}
