package com.pamihnenkov.supplier.model;

import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@Table(name = "departments",uniqueConstraints = @UniqueConstraint(columnNames = {"name","organization_id"}))
public class Department extends BaseEntity {

        private String name;
    @ManyToOne
        private Organization organization;
    @ManyToOne
        private ApplicationUser supplier;
    @OneToOne
        private ApplicationUser leader;

    public Department(String name, Organization organization) {
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
