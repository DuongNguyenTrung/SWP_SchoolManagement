package com.swp.SchoolManagement.config.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GlobalExeption extends RuntimeException {
    private int code;
    private String message;

}
