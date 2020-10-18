package com.pamihnenkov.supplier.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@Table(name = "requests")
public class Request extends BaseEntity{

    @ManyToOne
        private User author;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Type(type = "date")
        private Date date;
        private String goal;
    @OneToMany(mappedBy = "request")
        private List<RequestLine> requestLines = new ArrayList<>();

    @Override
        public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

        if (!getAuthor().equals(request.getAuthor())) return false;
        if (!getDate().equals(request.getDate())) return false;
        return getId() != null ? getId().equals(request.getId()) : request.getId() == null;
    }


    @Override
    public String toString(){
        return "Request{" +
                "author=" + getAuthor().getSurname() +
                ", date=" + getDate() +
                ", goal=" + getGoal() +
                ",  requestLines[" + getRequestLines().size()+"]}";
    }


}
