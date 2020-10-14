package com.pamihnenkov.supplier.service;

import com.pamihnenkov.supplier.model.Request;
import com.pamihnenkov.supplier.model.User;

import java.util.List;

public interface RequestService extends CrudService<Request, Long>{
    List<Request> findByAuthor(User user);
}
