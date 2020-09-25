package com.pamihnenkov.supplier.security.ApplicationUser;

import com.pamihnenkov.supplier.service.CrudService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface ApplicationUserService extends CrudService<ApplicationUser,Long>,UserDetailsService {

}
