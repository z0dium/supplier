package com.pamihnenkov.supplier.model;

import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import java.util.Set;

public class Contragent extends BaseEntity{

    private Integer inn_code;
    private String name;
    private Set<Member> member;
    private String siteAdress;
    private String accountatEmail;

}
