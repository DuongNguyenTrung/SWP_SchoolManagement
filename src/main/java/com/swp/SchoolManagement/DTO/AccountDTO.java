package com.swp.SchoolManagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private long id;
    private String email;
    private String fullname;
    private String role;
    private int status;
}
