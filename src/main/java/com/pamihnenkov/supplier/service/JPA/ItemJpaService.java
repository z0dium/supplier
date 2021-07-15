package com.pamihnenkov.supplier.service.JPA;

import com.pamihnenkov.supplier.model.Category;
import com.pamihnenkov.supplier.model.Item;
import com.pamihnenkov.supplier.service.repository.ItemRepository;
import com.pamihnenkov.supplier.service.serviceInterfaces.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ItemJpaService implements ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemJpaService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    @Override
    public Set<Item> findAllByCategory(Category category) {
        return itemRepository.findAllByCategory(category);
    }

    @Override
    public Set<Item> findAll() {
        return new HashSet<>(itemRepository.findAll());
    }

    @Override
    public Item findById(Long aLong) {
        return itemRepository.findById(aLong).orElse(null);
    }

    @Override
    public Item save(Item object) {

        if(object.getId() != null){ // If Id isNotNull then just store object
            return  itemRepository.save(object);
        }
        else {                      // If Id isNull. But there are still exist possible that new instance of entity is same to stored.
                                    // Then object is loaded from db. When it new Entity it stored.
            Item loaded = itemRepository.findByNameAndModel(object.getName(),object.getModel()).orElse(null);
            if (loaded == null) loaded = itemRepository.save(object);
            return loaded;
        }
    }

    @Override
    public void delete(Item object) {
        itemRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        itemRepository.deleteById(aLong);
    }
}
