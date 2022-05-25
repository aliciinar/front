package com.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
/**
 * Converts Json to object.
 */
public class LoginDto {


    private String name;
    private String password;
}
