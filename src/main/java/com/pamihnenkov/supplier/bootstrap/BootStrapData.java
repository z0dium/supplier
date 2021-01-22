package com.pamihnenkov.supplier.bootstrap;

import com.pamihnenkov.supplier.model.*;
import com.pamihnenkov.supplier.security.ApplicationGrantedAuthority;
import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUser;
import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUserService;
import com.pamihnenkov.supplier.service.DepartmentService;
import com.pamihnenkov.supplier.service.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
@AllArgsConstructor
public class BootStrapData implements CommandLineRunner {


    private final DepartmentService departmentService;
    private final RequestService requestService;
    private final PasswordEncoder passwordEncoder;
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
        req2.setUnitOfMeasure(UnitOfMeasures.пар);

        RequestLine req3 = new RequestLine();
        req3.setItem(bolt);
        req3.setOrderedQuantity(56);
        req3.setDescription("M12*150");
        req3.setUnitOfMeasure(UnitOfMeasures.шт);

        RequestLine req4 = new RequestLine();
        req4.setItem(siz);
        req4.setOrderedQuantity(1000);
        req4.setDescription("c Пвх, 5нитка");
        req4.setUnitOfMeasure(UnitOfMeasures.пар);


        List<RequestLine> list = new ArrayList<>();
        list.add(req1);
        list.add(req2);

        List<RequestLine> list2 = new ArrayList<>();
        list2.add(req3);
        list2.add(req4);

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
        admin.getAuthorities().add(ApplicationGrantedAuthority.ROLE_SUPPLIER);

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

        Department department = new Department("Энергоцех", "СиБ-центр");
        department.setSupplier(admin);
        departmentService.save(department);

        Department department2 = new Department("Цех сваи", "СиБ-центр");
        department2.setSupplier(user);
        departmentService.save(department2);

        Request request = new Request();
        request.setAuthor(user);
        request.setDepartment(department2);
        //       request.setNumber(1);
        request.setRequestLines(list);
        request.setDate(new Date());

        requestService.save(request);


        Request request2 = new Request();
        request2.setAuthor(admin);
        request2.setDepartment(department);
        request2.setRequestLines(list2);
        request2.setDate(new Date());

        requestService.save(request2);
    }
}
