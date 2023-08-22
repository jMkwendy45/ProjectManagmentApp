package com.duru.schoolManagement.service;

import com.duru.schoolManagement.dto.request.LoginRequest;
import com.duru.schoolManagement.dto.request.SignUpRequest;
import com.duru.schoolManagement.dto.response.JwtAuthenticationResponse;
import com.duru.schoolManagement.dto.response.LoginResponse;

public interface AuthenticationService {

    JwtAuthenticationResponse signUp(SignUpRequest request);
    LoginResponse login(LoginRequest request);


}
