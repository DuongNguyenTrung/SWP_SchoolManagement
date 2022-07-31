package com.swp.SchoolManagement.response;

import lombok.Data;

@Data
public class InfoDTO {
    private long id;
    private String email;
    private String fullname;
    private String address;
    private String phone;
    private String avatar;
    private String dob;
    private Boolean gender;

}
