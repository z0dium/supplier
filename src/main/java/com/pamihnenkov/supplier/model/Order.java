package com.pamihnenkov.supplier.model;

public class Order extends BaseEntity{

    private Request request;
    private Item item;
    private Integer orderedQuantity;
    private Integer deliveredQuantity;
    private Long expectedTo;
    private Contragent contragent;
    private Long deliveryDay;
}
