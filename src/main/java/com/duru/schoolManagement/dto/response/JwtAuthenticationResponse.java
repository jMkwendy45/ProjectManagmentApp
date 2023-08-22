package com.duru.schoolManagement.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class JwtAuthenticationResponse {
    private Long id;
    private String firstName;
    private String lastName;
  private   List<String>roles;
    private String email;

    public JwtAuthenticationResponse(Long userId, String firstName, String lastName, String email, List<String> role){
        this.id = userId;
        this.firstName =firstName;
        this.lastName = lastName;
        this.email =email;
        this.roles = role;
    }
}
