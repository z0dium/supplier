package com.pamihnenkov.supplier.model;

import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@Table(name = "categories")
public class Category extends BaseEntity{

        private String name;
    @ManyToOne
        private Category parentCategory;
    @ManyToMany(fetch = FetchType.LAZY)
        private List<ApplicationUser> subscribers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return name.equals(category.name) &&
                Objects.equals(parentCategory, category.parentCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, parentCategory);
    }

    @Override
    public String toString() {
        return  name + (parentCategory == null ? "" : "(" + parentCategory.getName() + ")");

    }
}
