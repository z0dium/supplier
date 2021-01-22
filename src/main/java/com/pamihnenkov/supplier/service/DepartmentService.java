package com.pamihnenkov.supplier.service;

import com.pamihnenkov.supplier.model.Department;
import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUser;

import java.util.List;
import java.util.Set;

public interface DepartmentService extends CrudService <Department, Long> {

    Set<Department> findBySupplier (ApplicationUser supplier);
}
