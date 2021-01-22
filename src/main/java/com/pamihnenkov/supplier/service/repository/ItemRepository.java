package com.pamihnenkov.supplier.service.repository;

import com.pamihnenkov.supplier.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

Optional<Item> findByNameAndModel(String name, String model);
}
