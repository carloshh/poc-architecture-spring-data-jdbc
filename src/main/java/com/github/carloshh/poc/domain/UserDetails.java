package com.github.carloshh.poc.domain;

import org.springframework.data.annotation.Id;

public class UserDetails {
    private @Id String id;
    private Long userId;
    private String email;

    public UserDetails(String id, Long userId, String email) {
        this.id = id;
        this.userId = userId;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "id='" + id + '\'' +
                ", userId=" + userId +
                ", email='" + email + '\'' +
                '}';
    }
}

