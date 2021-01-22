package com.pamihnenkov.supplier.model;

import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@Table(name = "departments")
public class Department extends BaseEntity {

        private String name;
        private String organization;
    @ManyToOne
        private ApplicationUser supplier;

    public Department(String name, String organization) {
        this.name = name;
        this.organization = organization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(organization, that.organization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, organization);
    }
}
