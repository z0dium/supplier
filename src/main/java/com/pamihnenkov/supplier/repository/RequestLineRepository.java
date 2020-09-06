package com.pamihnenkov.supplier.repository;

import com.pamihnenkov.supplier.model.RequestLine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestLineRepository extends CrudRepository <RequestLine, Long> {

}
