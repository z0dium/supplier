package com.pamihnenkov.supplier.service.JPA;

import com.pamihnenkov.supplier.model.Organization;
import com.pamihnenkov.supplier.service.repository.OrganozationRepository;
import com.pamihnenkov.supplier.service.serviceInterfaces.OrganizationService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrganizationJpaService implements OrganizationService {

    private final OrganozationRepository organozationRepository;

    public OrganizationJpaService(OrganozationRepository organozationRepository) {
        this.organozationRepository = organozationRepository;
    }

    @Override
    public List<Organization> findAllManaged() {
        List<Organization> result = new ArrayList<>();
        result.add(organozationRepository.findById(1L).get());
        result.add(organozationRepository.findById(2L).get());
        return result;
    }

    @Override
    public Organization findByInnCode(String inn) {
        return organozationRepository.findByInnCode(inn);
    }

    @Override
    public Set<Organization> findAll() {
        return new HashSet<>(organozationRepository.findAll());
    }

    @Override
    public Organization findById(Long aLong) {
        return organozationRepository.findById(aLong).orElse(null);
    }

    @Override
    public Organization save(Organization object) {
        return organozationRepository.save(object);
    }

    @Override
    public void delete(Organization object) {
        organozationRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        organozationRepository.deleteById(aLong);
    }
}
