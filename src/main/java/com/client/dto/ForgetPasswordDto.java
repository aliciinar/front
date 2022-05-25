package com.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Converts Json to object.
 */
@Data
@AllArgsConstructor
public class ForgetPasswordDto {
    private String name;
}
