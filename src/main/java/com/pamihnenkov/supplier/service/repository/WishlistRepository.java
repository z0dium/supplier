package com.pamihnenkov.supplier.service.repository;

import com.pamihnenkov.supplier.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface WishlistRepository extends JpaRepository<WishList,Long> {
}
