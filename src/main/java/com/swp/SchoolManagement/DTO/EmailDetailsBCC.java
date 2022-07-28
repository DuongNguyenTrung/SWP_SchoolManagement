package com.swp.SchoolManagement.DTO;

import java.util.List;

import lombok.Data;

@Data
public class EmailDetailsBCC {
    private List<String> recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}
