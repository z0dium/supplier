package com.pamihnenkov.supplier.repository;

import com.pamihnenkov.supplier.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {

}
