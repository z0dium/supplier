package com.pamihnenkov.supplier.service.repository;

import com.pamihnenkov.supplier.model.Department;
import com.pamihnenkov.supplier.model.Organization;
import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface DepartmentRepository extends CrudRepository<Department,Long>,JpaRepository<Department,Long> {
    Set<Department> findBySupplier (ApplicationUser supplier);
    Set<Department> findByLeader (ApplicationUser leader);
    Optional<Department> findByNameAndOrganization(String name, Organization organization);
}
