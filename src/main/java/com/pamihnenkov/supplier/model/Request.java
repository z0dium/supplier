package com.pamihnenkov.supplier.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@ToString
@Table(name = "requests")
public class Request extends BaseEntity{

    @ManyToOne(cascade = CascadeType.PERSIST)
    private User author;
    private Long date;
    private Integer number;
    private String goal;
    @OneToMany(mappedBy = "request", cascade = CascadeType.PERSIST)
    private List<RequestLine> requestLines = new ArrayList<>();

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
