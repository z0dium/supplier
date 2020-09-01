package com.pamihnenkov.supplier.model;

import javax.persistence.Entity;
import java.util.Set;

public class Member extends Human {

    private String email;
    private Set<Contragent> companies;
}
