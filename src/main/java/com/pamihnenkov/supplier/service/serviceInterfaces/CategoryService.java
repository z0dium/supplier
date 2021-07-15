package com.pamihnenkov.supplier.service.serviceInterfaces;

import com.pamihnenkov.supplier.model.Category;
import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUser;
import com.pamihnenkov.supplier.service.repository.CategoryRepository;

import java.util.List;

public interface CategoryService extends CrudService<Category,Long> {
    List<ApplicationUser> findAllSubscribersByCategory(Category category);
}
