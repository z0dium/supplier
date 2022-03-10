package com.pamihnenkov.supplier.service.serviceInterfaces;

import com.pamihnenkov.supplier.model.WishList;

import java.util.Set;

public interface WishlistService extends CrudService<WishList,Long> {
    Set<WishList> getWishlistsForCurrentUser ();
}
