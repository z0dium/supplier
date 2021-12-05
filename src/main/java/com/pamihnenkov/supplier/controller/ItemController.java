package com.pamihnenkov.supplier.controller;

import com.pamihnenkov.supplier.model.Category;
import com.pamihnenkov.supplier.model.Item;
import com.pamihnenkov.supplier.model.Request;
import com.pamihnenkov.supplier.service.serviceInterfaces.CategoryService;
import com.pamihnenkov.supplier.service.serviceInterfaces.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
@RequestMapping("/app")
public class ItemController {

    private final ItemService itemService;
    private final CategoryService categoryService;

    @Autowired
    public ItemController(ItemService itemService, CategoryService categoryService) {
        this.itemService = itemService;
        this.categoryService = categoryService;
    }

    @GetMapping("items")
    public String showAllItems(Model model){

        model.addAttribute("items", itemService.findAll());
        return "allItems";
    }

    @Secured(value = {"ROLE_SUPPLIER"})
    @GetMapping("items/{stringId}")
    public ModelAndView showItem(@PathVariable String stringId){
        ModelAndView mav = new ModelAndView();
        try {
            Long id = Long.parseLong(stringId);
            // logging
            Item item = itemService.findById(id);
            if (!(item == null)) {
                mav.addObject("item", item);
                mav.addObject("listOfCategories",categoryService.findAll());
                mav.setViewName("editItem");
                return mav;
            } else {
                mav.addObject("message","Такого ТМЦ не существует.");
                return mav;
            }
        } catch(NumberFormatException nfe) {
            mav.addObject("message","'"+stringId+"'" + " не является корректным номером ТМЦ.");
        }

        mav.setViewName("allItems");
        return mav;
    }

    @Secured(value = {"ROLE_SUPPLIER"})
    @PostMapping("items/save")
    public String saveItem(@ModelAttribute Item item) {

        if (item.getId() != null) {
            Category category = item.getCategory();
            Item temp = itemService.findById(item.getId());
            temp.setCategory(category);
            itemService.save(temp);
        } else {
            itemService.save(item);
        }
        return "redirect:/app/items";
    }
}
