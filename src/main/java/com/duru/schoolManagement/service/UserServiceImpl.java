package com.duru.schoolManagement.service;

import com.duru.schoolManagement.data.model.User;
import com.duru.schoolManagement.data.repository.UserRepository;
import com.duru.schoolManagement.security.UserDetailImplemenation;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl  implements UserDetailsService{
     private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(
                ()->new UsernameNotFoundException("user with these email not found "+username));
        return UserDetailImplemenation.build(user);
    }
}
