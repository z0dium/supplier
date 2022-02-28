package com.pamihnenkov.supplier.model;

import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "wishlists")
public class WishList extends BaseEntity{

    @ManyToOne
        private ApplicationUser author;
    @ManyToOne
        private Department department;
        private String goal;
    @OneToMany
        private List<RequestLine> requestLines = new ArrayList<>();
}
