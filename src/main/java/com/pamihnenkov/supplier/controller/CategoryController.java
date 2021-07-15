package com.pamihnenkov.supplier.controller;

import com.pamihnenkov.supplier.model.Category;
import com.pamihnenkov.supplier.service.serviceInterfaces.CategoryService;
import com.pamihnenkov.supplier.service.serviceInterfaces.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/app/admin")
public class CategoryController {

    private final ItemService itemService;
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(ItemService itemService, CategoryService categoryService) {
        this.itemService = itemService;
        this.categoryService = categoryService;
    }

    @GetMapping("categories")
    public ModelAndView showAllCategories() {
        ModelAndView mav = new ModelAndView("allCategories");

        mav.addObject("listOfCategories",categoryService.findAll());
        return mav;
    }

    @Secured(value = {"ROLE_ADMIN"})
    @GetMapping("categories/create")
    public ModelAndView createNewCategory(){
        ModelAndView mav = new ModelAndView();
        Category category = new Category();

        mav.addObject("listOfCategories",categoryService.findAll());
        mav.addObject("category", category);
        mav.setViewName("editCategory");
        return  mav;
    }

    @Secured(value = {"ROLE_ADMIN"})
    @PostMapping("categories/save")
    public String saveCategory(@ModelAttribute Category category){
        Long id = category.getId();
        if (id==null){
            categoryService.save(category);
        } else {
            Category persist =  categoryService.findById(id);;
            persist.setSubscribers(category.getSubscribers());
        }

        return "redirect:/app/admin/categories";
    }

}
