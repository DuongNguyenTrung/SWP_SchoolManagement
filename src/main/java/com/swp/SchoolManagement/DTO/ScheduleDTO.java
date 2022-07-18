package com.swp.SchoolManagement.DTO;

import lombok.Data;

@Data
public class ScheduleDTO {
    private String studentCode;
    private String name;
    private String className;
    private String subjectName;
    private int slot;
    private String dayId;
}
