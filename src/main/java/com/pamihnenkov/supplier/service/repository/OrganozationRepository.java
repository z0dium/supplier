package com.pamihnenkov.supplier.service.repository;

import com.pamihnenkov.supplier.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganozationRepository extends JpaRepository<Organization, Long> {

    Organization findByInnCode(String inn);
}
