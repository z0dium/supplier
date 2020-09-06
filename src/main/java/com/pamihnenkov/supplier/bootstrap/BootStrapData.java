package com.pamihnenkov.supplier.bootstrap;

import com.pamihnenkov.supplier.model.Item;
import com.pamihnenkov.supplier.model.Request;
import com.pamihnenkov.supplier.model.RequestLine;
import com.pamihnenkov.supplier.model.User;
import com.pamihnenkov.supplier.repository.ItemRepository;
import com.pamihnenkov.supplier.repository.RequestLineRepository;
import com.pamihnenkov.supplier.repository.RequestRepository;
import com.pamihnenkov.supplier.repository.UserRepository;
import com.pamihnenkov.supplier.service.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class BootStrapData implements CommandLineRunner {


    private final RequestService requestService;



    @Override
    public void run(String... args) throws Exception {
        Item bolt = Item.builder()
            .name("Болт")
            .serialNumber("DIN 912")
            .description("M12*110")
        .build();
   //     itemRepository.save(bolt);

        Item siz = Item.builder()
            .name("Перчатки хб")
            .description("c Пвх, 5нитка")
        .build();
  //      itemRepository.save(siz);

        RequestLine req1 = RequestLine.builder()
                .item(siz)
                .orderedQuantity(1000)
                .build();
 //       requestLineRepository.save(req1);

        RequestLine req2 = RequestLine.builder()
                .item(bolt)
                .orderedQuantity(50)
                .build();

        Set<RequestLine> set = new HashSet<>();
        set.add(req1);
        set.add(req2);

        User user = User.builder()
                .name("Павел")
                .surname("Михненков")
                .email("sibsnab1@gmail.com")
        .build();

        Request request = Request.builder()
                .author(user)
                .number(3565)
                .requestLines(set)
                .date(System.currentTimeMillis())
                .build();
        request.getApprovers().add(user);


        requestService.save(request);
    }
}
