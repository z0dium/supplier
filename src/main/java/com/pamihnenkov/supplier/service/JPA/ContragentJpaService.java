package com.pamihnenkov.supplier.service.JPA;

import com.pamihnenkov.supplier.model.Contragent;
import com.pamihnenkov.supplier.service.ContragentService;
import com.pamihnenkov.supplier.service.repository.ContragentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ContragentJpaService implements ContragentService {

    private final ContragentRepository contragentRepository;

    @Autowired
    public ContragentJpaService(ContragentRepository contragentRepository) {
        this.contragentRepository = contragentRepository;
    }

    @Override
    public Set<Contragent> findAll() {
        return new HashSet<>(contragentRepository.findAll());
    }

    @Override
    public Contragent findById(Long aLong) {
        return contragentRepository.findById(aLong).orElse(null);
    }

    @Override
    public Contragent save(Contragent object) {
        return contragentRepository.save(object);
    }

    @Override
    public void delete(Contragent object) {
        contragentRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        contragentRepository.deleteById(aLong);
    }
}
