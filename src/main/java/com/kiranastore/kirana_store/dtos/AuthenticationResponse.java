package com.kiranastore.kirana_store.dtos;


public class AuthenticationResponse {
    private String token;
    private Long userId;
    private String userName;

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
}
