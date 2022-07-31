package com.swp.SchoolManagement.response;

import lombok.Data;

@Data
public class UpdateInfoReq {
    public String fullname;
    public String address;
    public String phone;
    public String avatar;
    public String dob;
    public boolean gender;
}
