package com.swp.SchoolManagement.DTO;

import lombok.Data;

@Data
public class AttendDTO {
    private String day;
    private int slot;
    private int status;
}
