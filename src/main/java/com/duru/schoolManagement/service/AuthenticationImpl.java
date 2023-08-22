package com.duru.schoolManagement.service;

import com.duru.schoolManagement.data.model.Role;
import com.duru.schoolManagement.data.model.RoleType;
import com.duru.schoolManagement.data.model.User;
import com.duru.schoolManagement.data.repository.RoleRepository;
import com.duru.schoolManagement.data.repository.UserRepository;
import com.duru.schoolManagement.dto.request.LoginRequest;
import com.duru.schoolManagement.dto.request.SignUpRequest;
import com.duru.schoolManagement.dto.response.JwtAuthenticationResponse;
import com.duru.schoolManagement.dto.response.LoginResponse;
import com.duru.schoolManagement.exception.AlreadyExistException;
import com.duru.schoolManagement.security.JwtService;
import com.duru.schoolManagement.security.UserDetailImplemenation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class AuthenticationImpl  implements  AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private AuthenticationManager authenticationManager;

    private final RoleRepository roleRepository;

//    private  final PasswordEncoder encoder;

//    public AuthenticationImpl(UserRepository userRepository, JwtService jwtService, AuthenticationManager authenticationManager, RoleRepository roleRepository, PasswordEncoder encoder) {
//        this.userRepository = userRepository;
//        this.jwtService = jwtService;
//        this.authenticationManager = authenticationManager;
//        this.roleRepository = roleRepository;
//        this.encoder = encoder;
//    }

    @Override
    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AlreadyExistException("EmailAlreadyExist");
        }
        Role userRole = new Role();
        userRole.setRoleType(RoleType.WRITER);
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);


        roleRepository.save(userRole);

        User user = new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(request.getLastName());
        user.setRole(roles);

        userRepository.save(user);
        List<String> role = user.getRole().stream()
                .map(item -> item.getRoleType().name())
                .collect(Collectors.toList());

        return new JwtAuthenticationResponse(user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                role);

    }

    @Override
    public LoginResponse login(LoginRequest request) {
//        Authentication authentication =authenticationManager
//                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        UserDetailImplemenation userDetailImplemenation = (UserDetailImplemenation) authentication.getPrincipal();
//
//       ResponseCookie jwtCookie = jwtService.generateCookie(userDetailImplemenation);
//        Optional<User> user = userRepository.findByEmail(userDetailImplemenation.getEmail());
//        List<String> roles = user.get().getRole().stream()
//               .map(item ->item.getRoleType().name())
//                .collect(Collectors.toList());
//
//
//
//        return new LoginResponse(userDetailImplemenation.getId(), userDetailImplemenation.getFirstName(),
//               userDetailImplemenation.getLastName(),
//                jwtCookie.getValue());
//
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder()
//    {
//        return new BCryptPasswordEncoder();
//    }
        return null;
    }
}