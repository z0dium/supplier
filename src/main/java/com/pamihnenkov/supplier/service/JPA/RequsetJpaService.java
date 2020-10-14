package com.pamihnenkov.supplier.service.JPA;

import com.pamihnenkov.supplier.model.Request;
import com.pamihnenkov.supplier.model.User;
import com.pamihnenkov.supplier.repository.RequestLineRepository;
import com.pamihnenkov.supplier.repository.RequestRepository;
import com.pamihnenkov.supplier.repository.UserRepository;
import com.pamihnenkov.supplier.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RequsetJpaService implements RequestService {

    private final RequestRepository requestRepository;
    private final RequestLineRepository requestLineRepository;


    @Autowired
    public RequsetJpaService(RequestRepository requestRepository, RequestLineRepository requestLineRepository) {

        this.requestRepository = requestRepository;
        this.requestLineRepository = requestLineRepository;
    }

    @Override
    public Set<Request> findAll() {
        return new HashSet<>(requestRepository.findAll());
    }

    @Override
    public List<Request> findByAuthor(User user){
        return new ArrayList<>(requestRepository.findByAuthor(user));
    }

    @Override
    public Request findById(Long aLong) {
        return requestRepository.findById(aLong).orElse(null);
    }

    @Override
    @Transactional
    public Request save(Request object) {
        object.getRequestLines().stream()
                .peek(requestLine -> requestLine.setRequest(object))
                .forEach(requestLineRepository::save);

        System.out.println(object);
        return requestRepository.save(object);
    }

    @Override
    public void delete(Request object) {
        requestRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        requestRepository.deleteById(aLong);
    }
}
