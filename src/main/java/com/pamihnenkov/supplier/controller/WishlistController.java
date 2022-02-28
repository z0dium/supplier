package com.pamihnenkov.supplier.controller;

import com.pamihnenkov.supplier.model.Request;
import com.pamihnenkov.supplier.model.WishList;
import com.pamihnenkov.supplier.service.serviceInterfaces.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/app")
public class WishlistController {

    private final ApplicationUserService applicationUserService;
    private final DepartmentService departmentService;
    private final RequestLinesService requestLinesService;
    private final WishlistService wishlistService;

    public WishlistController(ApplicationUserService applicationUserService,
                              DepartmentService departmentService,
                              RequestLinesService requestLinesService,
                              WishlistService wishlistService) {
        this.applicationUserService = applicationUserService;
        this.departmentService = departmentService;
        this.requestLinesService = requestLinesService;
        this.wishlistService = wishlistService;
    }

    @GetMapping("wishlists")
    public String showAllWishlists(Model model){


        model.addAttribute("wishlist", wishlistService.findAll());
        model.addAttribute("listOfDepartments", departmentService.findAll());
        return "allWishlists";
    }

    @GetMapping("wishlists/{Id}")
    public ModelAndView showRequest(@PathVariable Long id, HttpServletRequest request){
        WishList wishList = wishlistService.findById(id);
        ModelAndView mav = new ModelAndView();
        if (wishList != null) {

            mav.addObject("container",wishList.getRequestLines());
            mav.addObject("request", wishList);
            mav.setViewName("allRequestsLines");
            return mav;
        } else {
            mav.addObject("message","Заявки с таким номером не существует.");
            mav.setViewName("redirect:"+request.getHeader("Referer"));
            return mav;
        }
    }

    @PostMapping("wishlists/save")
    public String saveWishlist(@ModelAttribute WishList wishList){
        wishList.setAuthor(applicationUserService.getCurrentUser());
        wishList.setDepartment(departmentService.findById(wishList.getDepartment().getId()));
        wishlistService.save(wishList);
        return "redirect:/app/wishlists";
    }
}
