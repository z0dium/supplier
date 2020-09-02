package com.pamihnenkov.supplier.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Builder
@Table(name = "requests")
public class Request extends BaseEntity{

    @ManyToOne(cascade = CascadeType.PERSIST)
    private User author;

    private String date;

    private Integer number;

    @OneToMany(mappedBy = "request", cascade = CascadeType.PERSIST)
    private Set<Requirement> requirements;

   // private Set<User> approvers;
}
