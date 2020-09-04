package com.pamihnenkov.supplier.bootstrap;

import com.pamihnenkov.supplier.model.Item;
import com.pamihnenkov.supplier.model.Request;
import com.pamihnenkov.supplier.model.Requirement;
import com.pamihnenkov.supplier.model.User;
import com.pamihnenkov.supplier.repository.ItemRepository;
import com.pamihnenkov.supplier.repository.RequestRepository;
import com.pamihnenkov.supplier.repository.RequirementRepository;
import com.pamihnenkov.supplier.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class BootStrapData implements CommandLineRunner {


    private final ItemRepository itemRepository;
    private final RequirementRepository requirementRepository;
    private final RequestRepository requestRepository;
    private final UserRepository userRepository;



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

        Requirement req1 = Requirement.builder()
                .item(siz)
                .orderedQuantity(1000)
                .build();
 //       requirementRepository.save(req1);

        Requirement req2 = Requirement.builder()
                .item(bolt)
                .orderedQuantity(50)
                .build();

        Set<Requirement> set = new HashSet<>();
        set.add(req1);
        set.add(req2);

        User user = User.builder()
                .name("Pavel")
                .surname("Mikhnenkov")
                .email("sibsnab1@gmail.com")
        .build();

   //     Request request = Request.builder()
   //             .author(user)
   //             .number(3565)
   //             .requirements(set)
   //             .date("Сейчас").build();
//
   //     requestRepository.save(request);
    }
}
