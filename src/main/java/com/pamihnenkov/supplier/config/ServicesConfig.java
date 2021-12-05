package com.pamihnenkov.supplier.config;

import com.pamihnenkov.supplier.model.Category;
import com.pamihnenkov.supplier.service.serviceInterfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class ServicesConfig {

    private final CategoryService categoryService;

    @Autowired
    public ServicesConfig(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Bean(name = "categoryServiceBean")
    public CategoryServiceBean categoryServiceBean(){
        return categoryService::findAll;
    }

    public interface CategoryServiceBean{
        Set<Category> getAllCategories();
    }
}
