package com.pamihnenkov.supplier.service.serviceInterfaces;

import com.pamihnenkov.supplier.model.Organization;

import java.util.Optional;

public interface OrganizationService extends CrudService <Organization, Long>{
    Organization findByInnCode(String inn);
}
