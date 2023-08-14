package com.duru.schoolManagement.security;

import com.duru.schoolManagement.data.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
public class UserDetailImplemenation implements UserDetails {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    @JsonIgnore
    private String password;
    private  Collection<? extends GrantedAuthority > authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    public static  UserDetailImplemenation build(User user){
        List<GrantedAuthority>authorities = user.getRole().stream().map(role ->
                new SimpleGrantedAuthority(role.getClass().getName())).collect(Collectors.toList());
        return  new UserDetailImplemenation(
                user.getUserId(), user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getPassword(), authorities);
    }
}
