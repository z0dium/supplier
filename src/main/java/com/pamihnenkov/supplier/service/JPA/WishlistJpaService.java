package com.pamihnenkov.supplier.service.JPA;

import com.pamihnenkov.supplier.model.WishList;
import com.pamihnenkov.supplier.service.repository.WishlistRepository;
import com.pamihnenkov.supplier.service.serviceInterfaces.RequestLinesService;
import com.pamihnenkov.supplier.service.serviceInterfaces.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class WishlistJpaService implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final RequestLinesService requestLinesService;

    @Autowired
    public WishlistJpaService(WishlistRepository wishlistRepository, RequestLinesService requestLinesService) {
        this.wishlistRepository = wishlistRepository;
        this.requestLinesService = requestLinesService;
    }

    @Override
    public Set<WishList> findAll() {
        return new HashSet<>(wishlistRepository.findAll());
    }

    @Override
    public WishList findById(Long aLong) {
        return wishlistRepository.findById(aLong).orElse(null);
    }

    @Override
    @Transactional
    public WishList save(WishList object) {
        object.getRequestLines().stream()
                .peek(requestLine -> requestLine.setRequest(null))
                .forEach(requestLinesService::save);

        return wishlistRepository.save(object);
    }

    @Override
    public void delete(WishList object) {
        wishlistRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        wishlistRepository.deleteById(aLong);
    }
}
