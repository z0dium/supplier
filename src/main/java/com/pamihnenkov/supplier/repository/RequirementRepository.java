package com.pamihnenkov.supplier.repository;

import com.pamihnenkov.supplier.model.Requirement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequirementRepository extends CrudRepository <Requirement, Long> {

}
