package com.pamihnenkov.supplier.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class User extends BaseEntity {

    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "contragent_user",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "contragent_id", referencedColumnName = "id"))
    private Set<Contragent> contragents = new HashSet<>();



    @Override
        public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!getName().equals(user.getName())) return false;
        if (!getSurname().equals(user.getSurname())) return false;
        return getEmail().equals(user.getEmail());
    }

    @Override
        public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getId().hashCode();
        result = 31 * result + getEmail().hashCode();
        return result;
    }
}
