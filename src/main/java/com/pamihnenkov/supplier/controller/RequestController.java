package com.pamihnenkov.supplier.controller;

import com.pamihnenkov.supplier.model.Department;
import com.pamihnenkov.supplier.model.Request;
import com.pamihnenkov.supplier.model.RequestLine;
import com.pamihnenkov.supplier.model.WishList;
import com.pamihnenkov.supplier.service.serviceInterfaces.ApplicationUserService;
import com.pamihnenkov.supplier.service.serviceInterfaces.DepartmentService;
import com.pamihnenkov.supplier.service.serviceInterfaces.RequestService;
import com.pamihnenkov.supplier.service.serviceInterfaces.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/app")
public class RequestController {

    private final RequestService requestService;
    private final ApplicationUserService applicationUserService;
    private final DepartmentService departmentService;
    private final WishlistService wishlistService;

    @Autowired
    public RequestController(RequestService requestService, ApplicationUserService applicationUserService, DepartmentService departmentService, WishlistService wishlistService) {
        this.requestService = requestService;
        this.applicationUserService = applicationUserService;
        this.departmentService = departmentService;
        this.wishlistService = wishlistService;
    }

    @GetMapping("requests")
    public String showAllRequests(Model model){

        model.addAttribute("requests", requestService.findAll().stream().filter(request -> request.getSigner() != null).collect(Collectors.toSet()));
        model.addAttribute("isNewRequestsExistsForSupplier",requestService.isNewRequestsExistsForSupplier());
        model.addAttribute("listOfDepartmentsForCurrentSupplier", departmentService.findBySupplier(applicationUserService.getCurrentUser()));
        return "allRequests";
    }

    @GetMapping("requests/all")
    public ModelAndView showAllRequestLines(){

        ModelAndView mav = new ModelAndView();
        List<RequestLine> container = new ArrayList<>();
        Comparator<RequestLine> nameComparator = Comparator.comparing(h -> h.getItem().getName().toLowerCase());
        Comparator<RequestLine> numberComparator = Comparator.comparing(h -> h.getRequest().getId());

        container = (requestService.findAll().stream()
                .flatMap(request -> request.getRequestLines().stream())
                .sorted(nameComparator.reversed())
                .sorted(numberComparator)
                .collect(Collectors.toList()));

        mav.addObject("container",container);
        mav.addObject("isNewRequestsExistsForSupplier",requestService.isNewRequestsExistsForSupplier());
        mav.addObject("listOfDepartmentsForCurrentSupplier", departmentService.findBySupplier(applicationUserService.getCurrentUser()));
        mav.setViewName("allRequestsLines");
        return mav;
    }

    @GetMapping("requests/{requestId}")
    public ModelAndView showRequest(@PathVariable Long requestId, HttpServletRequest request){
        Request req = requestService.findById(requestId);
        ModelAndView mav = new ModelAndView();
        if (req != null) {

            mav.addObject("container",req.getRequestLines());
            mav.addObject("request", req);
            mav.addObject("isNewRequestsExistsForSupplier",requestService.isNewRequestsExistsForSupplier());
            mav.setViewName("allRequestsLines");
            return mav;
            } else {
                mav.addObject("message","Заявки с таким номером не существует.");
                mav.setViewName("redirect:/app/requests");
                return mav;
            }
    }

    @GetMapping("requests/create")
    public String showCreateForm(Model model) {

        WishList wishlist = new WishList();
    //    request.setDepartment(new Department());
        wishlist.setRequestLines(List.of(new RequestLine()));   //1 empty string needed for better vision on fronted
        Iterable<Department> departments = (departmentService.findAll());  // список компаний для селектов
        model.addAttribute("wishlist", wishlist);
        model.addAttribute("departments",departments);
        model.addAttribute("isNewRequestsExistsForSupplier",requestService.isNewRequestsExistsForSupplier());
        model.addAttribute("listOfDepartmentsForCurrentSupplier", departmentService.findBySupplier(applicationUserService.getCurrentUser()));

        return "newRequest";
    }

    @Transactional
    @PostMapping("requests/create")
    public String createRequest(@ModelAttribute WishList wishlist) {
        if (wishlist.getDepartment() == null) wishlist.setDepartment(departmentService.findById(wishlist.getDepartment().getId()));
        if (wishlist.getAuthor() == null) wishlist.setAuthor(applicationUserService.getCurrentUser());
        Request newRequest = new Request(wishlist);
        newRequest.setDate(new Date());
        requestService.save(newRequest);
        wishlistService.delete(wishlist);
        return "redirect:/app/requests";
    }

    @PostMapping("requests/save")
    public String saveRequest(@ModelAttribute Request request) {
        requestService.save(request);
        return "redirect:/app/requests";
    }



    @Secured("ROLE_SUPPLIER")
    @PostMapping("requests/{id}/update")
    public String updateRequest(@PathVariable String id){
        Long longId = Long.parseLong(id);
        Request request = requestService.findById(longId);
        request.setSupplier(applicationUserService.getCurrentUser());
        requestService.save(request);
        return "redirect:/app/supplier/checking";
    }



}
