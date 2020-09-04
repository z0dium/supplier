package com.pamihnenkov.supplier.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "requests")
public class Request extends BaseEntity{

    @ManyToOne(cascade = CascadeType.PERSIST)
    private User author;
    private String date;
    private Integer number;
    private String goal;
    @OneToMany(mappedBy = "request", cascade = CascadeType.PERSIST)
    private Set<Requirement> requirements;
//    private Set<User> approvers;
    private boolean approved;
}
