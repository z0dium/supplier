package com.pamihnenkov.supplier.service.JPA;

import com.pamihnenkov.supplier.model.Request;
import com.pamihnenkov.supplier.repository.RequestRepository;
import com.pamihnenkov.supplier.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RequsetJPAService implements RequestService {

    private final RequestRepository requestRepository;

    @Autowired
    public RequsetJPAService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public Set<Request> findAll() {
        Set<Request> requests = new HashSet<>();
        requestRepository.findAll().forEach(requests::add);
        return requests;
    }

    @Override
    public Request findById(Long aLong) {
        return requestRepository.findById(aLong).orElse(null);
    }

    @Override
    public Request save(Request object) {

        object.getRequestLines().stream()
                .forEach(requestLine -> requestLine.setRequest(object));
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
