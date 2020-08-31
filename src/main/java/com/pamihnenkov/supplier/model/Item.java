package com.pamihnenkov.supplier.model;

public class Item {
    private Long id;
    private Unit unit;
    private Integer orderedQuantity;
    private Integer deliveredQuantity;
    private Long expectedTo;
    private Order order;
    private Contragent contragent;
}
