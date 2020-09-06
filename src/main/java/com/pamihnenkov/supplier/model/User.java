package com.pamihnenkov.supplier.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Builder
@Table(name = "users")
public class User extends BaseEntity{

    private String name;
    private String surname;
    private String phoneNumber;
    private String email;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "contragent_user",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "contragent_id", referencedColumnName = "id"))
    private Set<Contragent> contragents;

}
