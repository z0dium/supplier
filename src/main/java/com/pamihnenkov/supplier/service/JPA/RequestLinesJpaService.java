package com.pamihnenkov.supplier.service.JPA;

import com.pamihnenkov.supplier.model.RequestLine;
import com.pamihnenkov.supplier.service.repository.RequestLineRepository;
import com.pamihnenkov.supplier.service.serviceInterfaces.ItemService;
import com.pamihnenkov.supplier.service.serviceInterfaces.RequestLinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Service
public class RequestLinesJpaService implements RequestLinesService {

    private final RequestLineRepository requestLineRepository;
    private final ItemService itemService;

    @Autowired
    public RequestLinesJpaService(RequestLineRepository requestLineRepository, ItemService itemService) {
        this.requestLineRepository = requestLineRepository;
        this.itemService = itemService;
    }

    @Override
    public Set<RequestLine> findAll() {
        return new HashSet<>(requestLineRepository.findAll());
    }

    @Override
    public RequestLine findById(Long aLong) {
        return requestLineRepository.findById(aLong).orElse(null);
    }

    @Override
    public RequestLine save(RequestLine object) {
        object.setItem(itemService.save(object.getItem()));
        if (object.getExpectedTo()==null) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, 7);
            object.setExpectedTo(cal.getTime());
        }
        return requestLineRepository.save(object);
    }

    @Override
    public void delete(RequestLine object) {
        requestLineRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        requestLineRepository.deleteById(aLong);
    }
}
