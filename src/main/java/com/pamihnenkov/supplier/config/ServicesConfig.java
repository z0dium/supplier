package com.pamihnenkov.supplier.config;

import com.pamihnenkov.supplier.model.Category;
import com.pamihnenkov.supplier.model.Item;
import com.pamihnenkov.supplier.service.serviceInterfaces.CategoryService;
import com.pamihnenkov.supplier.service.serviceInterfaces.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class ServicesConfig {

    private final CategoryService categoryService;
    private final ItemService itemService;

    @Autowired
    public ServicesConfig(CategoryService categoryService, ItemService itemService) {
        this.categoryService = categoryService;
        this.itemService = itemService;
    }

    @Bean(name = "categoryServiceBean")
    public CategoryServiceBean categoryServiceBean(){
        return categoryService::findAll;
    }

    public interface CategoryServiceBean{
        Set<Category> getAllCategories();
    }

    @Bean(name = "itemServiceBean")
    public ItemServiceBean itemServiceBean(){
        return itemService::findAll;
    }

    public interface ItemServiceBean{
        Set<Item> getAllItems();
    }
}
