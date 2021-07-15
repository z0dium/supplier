package com.pamihnenkov.supplier.service.repository;

import com.pamihnenkov.supplier.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CategoryRepository extends JpaRepository<Category,Long> {
List<Category> findAllByParentCategory (Category parentCategory);
}
