package com.pamihnenkov.supplier.model;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Human extends BaseEntity{

    private String name;
    private String surname;
    private String phoneNumber;
    private String birthDate;

}
