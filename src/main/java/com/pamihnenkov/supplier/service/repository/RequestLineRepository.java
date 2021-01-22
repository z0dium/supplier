package com.pamihnenkov.supplier.service.repository;

import com.pamihnenkov.supplier.model.RequestLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestLineRepository extends JpaRepository<RequestLine, Long> {

}
