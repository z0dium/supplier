package com.pamihnenkov.supplier.controller;

import com.pamihnenkov.supplier.model.Request;
import com.pamihnenkov.supplier.model.WishList;
import com.pamihnenkov.supplier.service.serviceInterfaces.ApplicationUserService;
import com.pamihnenkov.supplier.service.serviceInterfaces.RequestService;
import com.pamihnenkov.supplier.service.serviceInterfaces.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/app")
public class SignerController {

    private final RequestService requestService;
    private final ApplicationUserService applicationUserService;
    private final WishlistService wishlistService;

    @Autowired
    public SignerController(RequestService requestService, ApplicationUserService applicationUserService, WishlistService wishlistService) {
        this.requestService = requestService;
        this.applicationUserService = applicationUserService;
        this.wishlistService = wishlistService;
    }

    @Secured("ROLE_SIGNER")
    @GetMapping("signer/checking")
    public String showAllRequests(Model model){

        model.addAttribute("requests", requestService.findAllUnsigned().stream()
                                                        .filter(request -> request.getSupplier() != null)
                                                        .collect(Collectors.toList()));
        return "allRequests";
    }

    @Secured("ROLE_SIGNER")
    @PostMapping("signer/{id}/approve")
    public String approveRequest(@PathVariable String id){
        Long longId = Long.parseLong(id);
        Request request = requestService.findById(longId);
        request.setSigner(applicationUserService.getCurrentUser());
        requestService.save(request);
        return "redirect:/app/signer/checking";
    }

    @Secured("ROLE_SIGNER")
    @PostMapping("signer/{id}/cancel")
    @Transactional
    public String cancelRequest(@PathVariable String id){
        Long longId = Long.parseLong(id);
        Request request = requestService.findById(longId);
        WishList wishList = new WishList();
        wishList.setRequestLines(request.getRequestLines());
        wishList.setGoal(request.getGoal());
        wishList.setDepartment(request.getDepartment());
        wishList.setAuthor(request.getAuthor());
        wishlistService.save(wishList);
        requestService.delete(request);
        return "redirect:/app/signer/checking";
    }
}
