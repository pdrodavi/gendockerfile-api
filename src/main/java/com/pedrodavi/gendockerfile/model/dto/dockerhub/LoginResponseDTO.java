package com.pedrodavi.gendockerfile.model.dto.dockerhub;

public class LoginResponseDTO {
    private String token;
    private String refresh_token;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(String token, String refresh_token) {
        this.token = token;
        this.refresh_token = refresh_token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    @Override
    public String toString() {
        return "LoginResponseDTO{" +
                "token='" + token + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                '}';
    }
}
