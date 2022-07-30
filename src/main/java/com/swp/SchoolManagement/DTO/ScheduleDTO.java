package com.swp.SchoolManagement.DTO;

import lombok.Data;

@Data
public class ScheduleDTO {
    private String className;
    private String subjectName;
    private int slot;
    private String dayId;
    private String weekday;
    private String time;
    private int statusAttend;
}
