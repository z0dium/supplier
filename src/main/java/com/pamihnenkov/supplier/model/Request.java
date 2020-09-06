package com.pamihnenkov.supplier.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
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
    private Long date;
    private Integer number;
    private String goal;
    @OneToMany(mappedBy = "request", cascade = CascadeType.PERSIST)
    private Set<RequestLine> requestLines;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

        if (!getAuthor().equals(request.getAuthor())) return false;
        if (!getDate().equals(request.getDate())) return false;
        return getNumber() != null ? getNumber().equals(request.getNumber()) : request.getNumber() == null;
    }

    @Override
    public int hashCode() {
        int result = getAuthor().hashCode();
        result = 31 * result + getDate().hashCode();
        result = 31 * result + (getNumber() != null ? getNumber().hashCode() : 0);
        return result;
    }
}
