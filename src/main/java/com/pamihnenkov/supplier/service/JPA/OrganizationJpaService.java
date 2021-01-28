package com.pamihnenkov.supplier.service.JPA;

import com.pamihnenkov.supplier.model.Organization;
import com.pamihnenkov.supplier.service.repository.OrganozationRepository;
import com.pamihnenkov.supplier.service.serviceInterfaces.OrganizationService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class OrganizationJpaService implements OrganizationService {

    private final OrganozationRepository organozationRepository;

    public OrganizationJpaService(OrganozationRepository organozationRepository) {
        this.organozationRepository = organozationRepository;
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
