package com.pamihnenkov.supplier.model;

import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@NoArgsConstructor
@Table(name = "requestLines")
public class RequestLine extends BaseEntity{

    @ManyToOne (cascade = CascadeType.PERSIST)
        private Request request;
    @ManyToOne
        private Item item;
    @Enumerated(EnumType.STRING)
        private UnitOfMeasures unitOfMeasure;
    @ManyToOne
        private Contragent contragent;
        private String description; //Any additional information
        private Integer orderedQuantity;
        private Integer deliveredQuantity;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Type(type = "date")
        private Date expectedTo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Type(type = "date")
        private Date deliveryDay;
}
