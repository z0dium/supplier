package com.pamihnenkov.supplier.service.repository;

import com.pamihnenkov.supplier.model.Department;
import com.pamihnenkov.supplier.model.Request;
import com.pamihnenkov.supplier.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByAuthor(User user);
    List<Request> findByDepartment(Department department);
    List<Request> findBySupplier(User user);

}
