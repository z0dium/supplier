package com.pamihnenkov.supplier.bootstrap;

import com.pamihnenkov.supplier.model.*;
import com.pamihnenkov.supplier.security.ApplicationGrantedAuthority;
import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUser;
import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUserService;
import com.pamihnenkov.supplier.service.ItemService;
import com.pamihnenkov.supplier.service.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
@AllArgsConstructor
public class BootStrapData implements CommandLineRunner {


    private final RequestService requestService;
    private PasswordEncoder passwordEncoder;
    private final ApplicationUserService applicationUserService;



    @Override
    public void run(String... args) throws Exception {
        Item bolt = new Item();
            bolt.setName("Болт");
            bolt.setModel("DIN 912");


        Item siz = new Item();
            siz.setName("Перчатки хб");


        RequestLine req1 = new RequestLine();
            req1.setItem(bolt);
            req1.setOrderedQuantity(1000);
            req1.setDescription("M12*110");
            req1.setUnitOfMeasure(UnitOfMeasures.шт);

        RequestLine req2 = new RequestLine();
            req2.setItem(siz);
            req2.setOrderedQuantity(50);
            req2.setDescription("c Пвх, 5нитка");


        List<RequestLine> list = new ArrayList<>();
            list.add(req1);
            list.add(req2);

        ApplicationUser admin = new ApplicationUser(new HashSet<ApplicationGrantedAuthority>(),
                                            passwordEncoder.encode("password"),
                                    true,
                                    true,
                                    true,
                                                true);
        admin.setName("Павел");
        admin.setSurname("Михненков");
        admin.setEmail("sibsnab1@gmail.com");
        admin.getAuthorities().add(ApplicationGrantedAuthority.ROLE_ADMIN);
        admin.getAuthorities().add(ApplicationGrantedAuthority.ROLE_USER);

        applicationUserService.save(admin);

        ApplicationUser user = new ApplicationUser(new HashSet<ApplicationGrantedAuthority>(),
                passwordEncoder.encode("user"),
                true,
                true,
                true,
                true);
        user.setName("Алексей");
        user.setSurname("Иванов");
        user.setEmail("snab@sib-centr.ru");
        user.getAuthorities().add(ApplicationGrantedAuthority.ROLE_USER);

        applicationUserService.save(user);

        Request request = new Request();
        request.setAuthor(user);
        request.setNumber(1);
        request.setRequestLines(list);
        request.setDate(new Date());

        requestService.save(request);
    }
}
