package com.pamihnenkov.supplier.service.JPA;

import com.pamihnenkov.supplier.model.Uom;
import com.pamihnenkov.supplier.repository.UomRepository;
import com.pamihnenkov.supplier.service.UomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UomJpaService implements UomService {

    private final UomRepository uomRepository;

    @Autowired
    public UomJpaService(UomRepository uomRepository) {
        this.uomRepository = uomRepository;
    }

    @Override
    public Set<Uom> findAll() {
        Set<Uom> uoms = new HashSet<>();
        uomRepository.findAll().forEach(uoms::add);
        return uoms;

    }

    @Override
    public Uom findById(Long aLong) {
       return uomRepository.findById(aLong).orElse(null);
    }

    @Override
    public Uom save(Uom object) {
        return uomRepository.save(object);
    }

    @Override
    public void delete(Uom object) {
        uomRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        uomRepository.deleteById(aLong);
    }
}
