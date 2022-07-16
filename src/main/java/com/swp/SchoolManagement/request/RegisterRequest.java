package com.swp.SchoolManagement.request;

import lombok.Data;

@Data
public class RegisterRequest {
    public String email;
    public String password;
    public String role;
}
