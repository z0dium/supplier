package com.pamihnenkov.supplier.bootstrap;

import com.pamihnenkov.supplier.model.Item;
import com.pamihnenkov.supplier.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {


    private final ItemRepository itemRepository;

    public BootStrapData(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Item bolt = new Item();
        bolt.setName("Болт");
        bolt.setSerialNumber("DIN 912");
        bolt.setDescription("M12*110");

        itemRepository.save(bolt);

        Item siz = new Item();
        siz.setName("Перчатки хб");
        siz.setDescription("c Пвх, 5нитка");

        itemRepository.save(siz);


    }
}
