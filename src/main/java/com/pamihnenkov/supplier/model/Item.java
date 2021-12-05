package com.pamihnenkov.supplier.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Class of unique good to purchase. Unique line of nomenclature
 */

@Data
@Entity
@NoArgsConstructor
@Table(name = "items",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "model"})} )
public class Item extends BaseEntity implements Serializable {

    private String name; //Subject. 1-2 words
    private String model; //Any combination to identify the DIN, ISO, serial number, and so on...
//    private String brand;
    @ManyToOne
    private Category category;
    private String urlOfExample;
    private String image;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        if (getName() != null ? !getName().equals(item.getName()) : item.getName() != null) return false;
        if (getModel() != null ? !getModel().equals(item.getModel()) : item.getModel() != null) return false;
        return getCategory() != null ? !getCategory().equals(item.getCategory()) : item.getCategory() != null;
    }

    @Override
    public int hashCode() {
        int result = (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getModel() != null ? getModel().hashCode() : 0);
        result = 31 * result + (getCategory() != null ? getCategory().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", category='" + category + '\'' +
                "} " + super.toString();
    }
}
