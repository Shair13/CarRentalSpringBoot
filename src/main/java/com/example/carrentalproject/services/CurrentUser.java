package com.example.carrentalproject.services;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
public class CurrentUser extends User {
    private final com.example.carrentalproject.model.User user;
    public CurrentUser(String username, String password,
                       Collection<? extends GrantedAuthority> authorities,
                       com.example.carrentalproject.model.User user) {
        super(username, password, authorities);
        this.user = user;
    }
    public com.example.carrentalproject.model.User getUser() {return user;}
}
