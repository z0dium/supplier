package com.pamihnenkov.supplier.repository;

import com.pamihnenkov.supplier.model.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {

}
