package com.pamihnenkov.supplier.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "uoms")
public class Uom extends BaseEntity{

    private String name;
}
