package com.example.couponsystem.Jwt;

public class UserNameAndPasswordAuthenticationRequest {
    private String email;
    private String password;

    public UserNameAndPasswordAuthenticationRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
