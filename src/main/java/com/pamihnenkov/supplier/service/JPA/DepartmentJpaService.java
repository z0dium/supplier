package com.pamihnenkov.supplier.service.JPA;

import com.pamihnenkov.supplier.model.Department;
import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUser;
import com.pamihnenkov.supplier.service.DepartmentService;
import com.pamihnenkov.supplier.service.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DepartmentJpaService implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentJpaService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Set<Department> findBySupplier(ApplicationUser supplier) {
        return departmentRepository.findBySupplier(supplier);
    }

    @Override
    public Set<Department> findAll() {
        return new HashSet<>(departmentRepository.findAll());
    }

    @Override
    public Department findById(Long aLong) {

        return departmentRepository.findById(aLong).orElse(null);
    }

    @Override
    public Department save(Department object) {

        return departmentRepository.save(object);
    }

    @Override
    public void delete(Department object) {
        departmentRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        departmentRepository.deleteById(aLong);
    }
}