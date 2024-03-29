package com.pamihnenkov.supplier.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@MappedSuperclass
@NoArgsConstructor
public class User extends BaseEntity {

        private String name;
        private String surname;
        private String phoneNumber;
        private String email;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "organization_user",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "organization_id", referencedColumnName = "id"))
        private Set<Organization> organizations = new HashSet<>();

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

        public String fio(){
            StringBuilder sb = new StringBuilder(this.surname).append(" ").append(this.name.charAt(0)).append(".");
            return sb.toString();
        }
    @Override
        public String toString(){
        return fio();
        }
}
