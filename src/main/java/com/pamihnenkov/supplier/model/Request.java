package com.pamihnenkov.supplier.model;

import lombok.Data;

import javax.persistence.Entity;
import java.util.Set;

@Data
@Entity
public class Request extends BaseEntity{
    private String author;
    private String date;
    private Integer number;
    private Set<Order> itemsOfOrder;
    private Set<User> approvers;
}
