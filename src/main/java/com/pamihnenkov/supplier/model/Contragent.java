package com.pamihnenkov.supplier.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "contragents")
public class Contragent extends BaseEntity{

    private Integer inn_code;
    private String name;
    @ManyToMany(mappedBy = "contragents")
    private Set<User> members;
    private String siteAdress;
    private String accountatEmail;

}
