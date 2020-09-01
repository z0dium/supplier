package com.pamihnenkov.supplier.model;

import java.util.Set;


public class Request extends BaseEntity{
    private String author;
    private String date;
    private Integer number;
    private Set<Requirement> itemsOfRequirement;
    private Set<User> approvers;
}
