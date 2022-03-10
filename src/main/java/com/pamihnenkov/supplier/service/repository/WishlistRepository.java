package com.pamihnenkov.supplier.service.repository;

import com.pamihnenkov.supplier.model.WishList;
import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface WishlistRepository extends JpaRepository<WishList,Long> {
    Set<WishList> findByAuthor (ApplicationUser user);
}
