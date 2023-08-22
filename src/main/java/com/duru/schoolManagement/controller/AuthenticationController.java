package com.duru.schoolManagement.controller;

import com.duru.schoolManagement.dto.request.LoginRequest;
import com.duru.schoolManagement.dto.request.SignUpRequest;
import com.duru.schoolManagement.dto.response.JwtAuthenticationResponse;
import com.duru.schoolManagement.dto.response.LoginResponse;
import com.duru.schoolManagement.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("/signin")
    public ResponseEntity<?>signIn(@RequestBody LoginRequest request){
        LoginResponse loginResponse =authenticationService.login(request);

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, loginResponse.getToken()).body(new LoginResponse(loginResponse.getId(), loginResponse.getFirstName(), loginResponse.getLastName(), loginResponse.getEmail(),
                loginResponse.getRoles(), loginResponse.getToken()));
    }

    @PostMapping("/register")
    public ResponseEntity<JwtAuthenticationResponse> signUp(@RequestBody SignUpRequest request){
        return ResponseEntity.ok(authenticationService.signUp(request));
    }

}
