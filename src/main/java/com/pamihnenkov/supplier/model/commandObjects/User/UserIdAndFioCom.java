package com.pamihnenkov.supplier.model.commandObjects.User;

import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUser;

public class UserIdAndFioCom {

    private final Long id;
    private final String fio;

    public UserIdAndFioCom(ApplicationUser user) {
        this.id = user.getId();
        this.fio = user.fio();
    }

    public Long getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }
}
