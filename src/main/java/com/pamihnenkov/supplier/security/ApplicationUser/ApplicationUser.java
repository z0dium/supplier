package com.pamihnenkov.supplier.security.ApplicationUser;

import com.pamihnenkov.supplier.model.User;
import com.pamihnenkov.supplier.security.ApplicationGrantedAuthority;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class ApplicationUser extends User implements UserDetails {

    @ElementCollection(targetClass = ApplicationGrantedAuthority.class,fetch = FetchType.EAGER)
    @JoinTable(name = "applicationGrantedAuthority", joinColumns = @JoinColumn (name = "applicationUserId"))
    @Column(name = "applicationGrantedAuthority", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<ApplicationGrantedAuthority> grantedAuthorities = new HashSet<>();
    private String password;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    public ApplicationUser(Collection<? extends   GrantedAuthority> grantedAuthorities, String password, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled) {


        this.grantedAuthorities = grantedAuthorities.stream()
                                                    .map(a->(ApplicationGrantedAuthority) a)
                                                    .collect(Collectors.toSet());
        this.password = password;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    @Override
    public Set<ApplicationGrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }



    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }



    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }



    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApplicationUser)) return false;
        if (!super.equals(o)) return false;

        ApplicationUser that = (ApplicationUser) o;

        if (!this.getUsername().equals(that.getUsername())) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
