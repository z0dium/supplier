package com.pamihnenkov.supplier.service.serviceInterfaces;

import com.pamihnenkov.supplier.model.Department;
import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUser;

import java.util.Set;

public interface DepartmentService extends CrudService <Department, Long> {

    Set<Department> findBySupplier (ApplicationUser supplier);
}
