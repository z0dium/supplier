package com.pamihnenkov.supplier.security.ApplicationUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Long>, JpaRepository<ApplicationUser, Long> {
    Optional<ApplicationUser> findFirstByEmail(String email);
}
