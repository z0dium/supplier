package com.pamihnenkov.supplier.security.ApplicationUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class ApplicationUserServiceImpl implements ApplicationUserService  {

    private final ApplicationUserRepository applicationUserRepository;

    @Autowired
    public ApplicationUserServiceImpl(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<ApplicationUser> applicationUser = applicationUserRepository.findFirstByEmail(s);
        return applicationUser.orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь %s не найден", s)));
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
