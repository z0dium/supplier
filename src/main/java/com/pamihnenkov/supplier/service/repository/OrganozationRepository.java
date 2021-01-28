package com.pamihnenkov.supplier.service.repository;

import com.pamihnenkov.supplier.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganozationRepository extends JpaRepository<Organization, Long> {
}
