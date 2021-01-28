package com.pamihnenkov.supplier.service.repository;

import com.pamihnenkov.supplier.model.Organization;
import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUser;
import org.hibernate.mapping.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Long>, JpaRepository<ApplicationUser, Long> {
    Optional<ApplicationUser> findFirstByEmail(String email);

    @Query(value = "SELECT * FROM application_user WHERE application_user.id IN (SELECT user_id FROM organization_user WHERE organization_id=:param)",nativeQuery = true)
    List<ApplicationUser> findByOrganizationId (@Param("param") Long organizationId);
}
