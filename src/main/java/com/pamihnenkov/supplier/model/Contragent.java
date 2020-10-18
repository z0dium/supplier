package com.pamihnenkov.supplier.model;

import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUser;
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
    private Set<ApplicationUser> members;
    private String siteAdress;
    private String accountatEmail;

}
