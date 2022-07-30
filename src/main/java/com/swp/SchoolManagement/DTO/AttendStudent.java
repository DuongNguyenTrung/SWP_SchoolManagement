package com.swp.SchoolManagement.DTO;

import lombok.Data;

@Data
public class AttendStudent {
    String studentCode;
    String fullname;
    String avatar;
    int status;
    Long attendId;
}
