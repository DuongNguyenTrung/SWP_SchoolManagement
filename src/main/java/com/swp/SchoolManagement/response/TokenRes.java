package com.swp.SchoolManagement.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenRes {
    String token;
    String username;
    String role;
    Long userId;
    String img;
}
