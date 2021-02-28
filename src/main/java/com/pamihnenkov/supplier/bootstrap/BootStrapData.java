package com.pamihnenkov.supplier.bootstrap;

import com.pamihnenkov.supplier.controller.DadataController;
import com.pamihnenkov.supplier.model.*;
import com.pamihnenkov.supplier.security.ApplicationGrantedAuthority;
import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUser;
import com.pamihnenkov.supplier.service.serviceInterfaces.ApplicationUserService;
import com.pamihnenkov.supplier.service.serviceInterfaces.DepartmentService;
import com.pamihnenkov.supplier.service.serviceInterfaces.OrganizationService;
import com.pamihnenkov.supplier.service.serviceInterfaces.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
@AllArgsConstructor
public class BootStrapData implements CommandLineRunner {

    private final OrganizationService organizationService;
    private final DepartmentService departmentService;
    private final RequestService requestService;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserService applicationUserService;



    @Override
    public void run(String... args) {

        DadataController test = new DadataController();
        test.printOrganization("780408250");

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

        Organization sib = new Organization();
        sib.setName("СиБ-центр");
        sib.setInnCode("7804008250");
        sib.setSiteAdress("sib-centr.ru");
        sib.setLegalForm("ООО");

        organizationService.save(sib);

        Organization gbi = new Organization();
        gbi.setName("ЖБИ №1 Рыбацкое");
        gbi.setInnCode("7811631610");
        gbi.setSiteAdress("gbi1.org");
        gbi.setLegalForm("ООО");

        organizationService.save(gbi);

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
        admin.getOrganizations().add(sib);

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
        user.getOrganizations().add(sib);
        user.getOrganizations().add(gbi);

        applicationUserService.save(user);

        Department department = new Department("Энергоцех", sib);
        department.setSupplier(admin);
        department.setLeader(admin);
        departmentService.save(department);

        Department department2 = new Department("Цех сваи", sib);
        department2.setSupplier(user);
        department2.setLeader(user);
        departmentService.save(department2);

        Department department3 = new Department("Арматурный цех", gbi);
        department3.setSupplier(admin);
        department3.setLeader(user);
        departmentService.save(department3);

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
