package com.pamihnenkov.supplier.service.serviceInterfaces;

import com.pamihnenkov.supplier.model.Department;
import com.pamihnenkov.supplier.model.Organization;
import com.pamihnenkov.supplier.model.Request;
import com.pamihnenkov.supplier.model.User;

import java.util.List;
import java.util.Set;

public interface RequestService extends CrudService<Request, Long>{
    List<Request> findByAuthor(User user);
    List<Request> findByDepartment(Department department);
    List<Request> findByDepartmentIn(Set<Department> departments);
    List<Request> findAllUnchecked();
    List<Request> findAllUnsigned();
    Boolean isNewRequestsExistsForSupplier();
    List<Request> findByOrganization(Organization organization);

}
