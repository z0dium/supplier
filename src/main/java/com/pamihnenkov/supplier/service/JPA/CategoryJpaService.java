package com.pamihnenkov.supplier.service.JPA;

import com.pamihnenkov.supplier.model.Category;
import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUser;
import com.pamihnenkov.supplier.service.repository.CategoryRepository;
import com.pamihnenkov.supplier.service.serviceInterfaces.CategoryService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CategoryJpaService implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryJpaService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> findAll() {
        return new HashSet<>(categoryRepository.findAll());
    }

    @Override
    public Category findById(Long aLong) {
        return categoryRepository.findById(aLong).orElse(null);
    }

    @Override
    public Category save(Category object) {
        return categoryRepository.save(object);
    }

    @Override
    public void delete(Category object) {
        categoryRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        categoryRepository.deleteById(aLong);
    }

    @Override
    public List<ApplicationUser> findAllSubscribersByCategory(Category category) {
        return category.getSubscribers();
    }
}
