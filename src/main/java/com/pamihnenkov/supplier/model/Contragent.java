package com.pamihnenkov.supplier.model;

import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUser;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "contragents",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"inn_code"})} )
public class Contragent extends BaseEntity{

        private String inn_code;
        private String name;
    @ManyToMany(mappedBy = "contragents")
        private Set<ApplicationUser> members = new HashSet<>();
        private String siteAdress;
        private String accountatEmail;

    @Override
    public String toString(){
        return name + " (" + inn_code + ")";
    }

}
