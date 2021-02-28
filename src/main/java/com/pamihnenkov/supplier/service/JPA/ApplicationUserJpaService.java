package com.pamihnenkov.supplier.service.JPA;

import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUser;
import com.pamihnenkov.supplier.service.serviceInterfaces.ApplicationUserService;
import com.pamihnenkov.supplier.service.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class ApplicationUserJpaService implements ApplicationUserService {

    private final ApplicationUserRepository applicationUserRepository;

    @Autowired
    public ApplicationUserJpaService(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    public ApplicationUser getCurrentUser() {
        return (ApplicationUser) loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    public Set<ApplicationUser> findByOrganizationId(Long organizationId) {
        return applicationUserRepository.findByOrganizationId(organizationId);
    }

    @Override
    public Set<ApplicationUser> findAllManagedUsers() {
        Set<ApplicationUser> result;
        result = (applicationUserRepository.findByOrganizationId(1l));
        result.addAll(applicationUserRepository.findByOrganizationId(2L));
        result.forEach(System.out::println);
        return result;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<ApplicationUser> applicationUser = applicationUserRepository.findFirstByEmail(email);
        return applicationUser.orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь %s не найден", email)));
    }

    @Override
    public Set<ApplicationUser> findAll() {
        return new HashSet<>(applicationUserRepository.findAll());
    }

    @Override
    public ApplicationUser findById(Long aLong) {
        return applicationUserRepository.findById(aLong).orElse(null);
    }

    @Override
    public ApplicationUser save(ApplicationUser object) {
        return applicationUserRepository.save(object);
    }

    @Override
    public void delete(ApplicationUser object) {
        applicationUserRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        applicationUserRepository.deleteById(aLong);
    }
}
