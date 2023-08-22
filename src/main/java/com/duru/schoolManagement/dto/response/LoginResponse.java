package com.duru.schoolManagement.dto.response;

import lombok.*;

import java.util.List;
@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<String>roles;
   private String token;

    public LoginResponse(Long id, String firstName, String lastName,String token) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.token = token;
    }
}
