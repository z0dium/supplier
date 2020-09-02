package com.pamihnenkov.supplier.repository;

import com.pamihnenkov.supplier.model.Request;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends CrudRepository<Request, Long> {
}
