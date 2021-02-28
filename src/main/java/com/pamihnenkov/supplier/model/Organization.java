package com.pamihnenkov.supplier.model;

import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "organizations",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"innCode"})} )
public class Organization extends BaseEntity{

        private String innCode;
        private String name;
        private String legalForm;
    @ManyToMany(mappedBy = "organizations")
        private Set<ApplicationUser> members = new HashSet<>();
        private String siteAdress;
        private String accountatEmail;

    @Override
    public String toString(){
        return innCode + ' ' + legalForm + ' ' + name;
    }

}
