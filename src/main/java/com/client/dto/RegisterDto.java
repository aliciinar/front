package com.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
/**
 * Converts Json to object.
 */
public class RegisterDto {


    private String name;
    private String password;
    private String email;
}
