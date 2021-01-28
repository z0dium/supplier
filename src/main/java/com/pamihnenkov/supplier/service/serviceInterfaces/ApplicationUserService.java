package com.pamihnenkov.supplier.service.serviceInterfaces;

import com.pamihnenkov.supplier.model.Organization;
import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface ApplicationUserService extends CrudService<ApplicationUser,Long>,UserDetailsService {
    ApplicationUser getCurrentUser();
    List<ApplicationUser> findByOrganizationId(Long organizationId);
}
