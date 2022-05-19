package com.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class RegisterDto {


    private String name;
    private String password;
    private String email;
}
