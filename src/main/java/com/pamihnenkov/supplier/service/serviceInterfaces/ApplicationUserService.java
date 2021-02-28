package com.pamihnenkov.supplier.service.serviceInterfaces;

import com.pamihnenkov.supplier.model.Organization;
import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;

public interface ApplicationUserService extends CrudService<ApplicationUser,Long>,UserDetailsService {
    ApplicationUser getCurrentUser();
    Set<ApplicationUser> findByOrganizationId(Long organizationId);
    Set<ApplicationUser> findAllManagedUsers();
}
