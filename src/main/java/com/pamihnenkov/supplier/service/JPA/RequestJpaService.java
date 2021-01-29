package com.pamihnenkov.supplier.service.JPA;

import com.pamihnenkov.supplier.model.Department;
import com.pamihnenkov.supplier.model.Request;
import com.pamihnenkov.supplier.model.User;
import com.pamihnenkov.supplier.service.repository.RequestRepository;
import com.pamihnenkov.supplier.service.serviceInterfaces.RequestLinesService;
import com.pamihnenkov.supplier.service.serviceInterfaces.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RequestJpaService implements RequestService {

    private final RequestRepository requestRepository;
    private final RequestLinesService requestLinesService;


    @Autowired
    public RequestJpaService(RequestRepository requestRepository, RequestLinesService requestLinesService) {

        this.requestRepository = requestRepository;
        this.requestLinesService = requestLinesService;
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
    public List<Request> findByDepartment(Department department) {
        return requestRepository.findByDepartment(department);
    }

    @Override // returns unsorted list of requests
    public List<Request> findByDepartmentIn(Set<Department> departments) {
        List<Request> result = new ArrayList<Request>();
        for (Department department: departments) {
            result.addAll(requestRepository.findByDepartment(department));
        }
        return result;
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
                .forEach(requestLinesService::save);
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
