package com.pamihnenkov.supplier.service.serviceInterfaces;
import com.pamihnenkov.supplier.model.Category;
import com.pamihnenkov.supplier.model.Item;

import java.util.Set;

public interface ItemService extends CrudService<Item, Long>{
    Set<Item> findAllByCategory(Category category);
}
