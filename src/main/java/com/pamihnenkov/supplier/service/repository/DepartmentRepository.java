package com.pamihnenkov.supplier.service.repository;

import com.pamihnenkov.supplier.model.Department;
import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
    Set<Department> findBySupplier (ApplicationUser supplier);
    Set<Department> findByLeader (ApplicationUser leader);
}
