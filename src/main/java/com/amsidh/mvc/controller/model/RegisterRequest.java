package com.amsidh.mvc.controller.model;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String emailId;
}
