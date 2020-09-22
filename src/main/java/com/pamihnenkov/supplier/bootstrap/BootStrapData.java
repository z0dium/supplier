package com.pamihnenkov.supplier.bootstrap;

import com.pamihnenkov.supplier.model.*;
import com.pamihnenkov.supplier.service.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
@AllArgsConstructor
public class BootStrapData implements CommandLineRunner {


    private final RequestService requestService;

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
            req1.setUnitOfMeasure(UnitOfMeasures.PCS);

        RequestLine req2 = new RequestLine();
            req2.setItem(siz);
            req2.setOrderedQuantity(50);
            req2.setDescription("c Пвх, 5нитка");


        List<RequestLine> list = new ArrayList<>();
            list.add(req1);
            list.add(req2);

        User user = new User();
                user.setName("Павел");
                user.setSurname("Михненков");
                user.setEmail("sibsnab1@gmail.com");


        Request request = new Request();
        request.setAuthor(user);
        request.setNumber(3565);
        request.setRequestLines(list);
        request.setDate(new Date());

        requestService.save(request);
    }
}
