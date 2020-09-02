package com.pamihnenkov.supplier.repository;

import com.pamihnenkov.supplier.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
}
