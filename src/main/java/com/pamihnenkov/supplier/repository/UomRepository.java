package com.pamihnenkov.supplier.repository;

import com.pamihnenkov.supplier.model.Uom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UomRepository extends CrudRepository<Uom, Long> {
}
