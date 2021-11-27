package com.adkan.adkan.config.dto;

import com.adkan.adkan.employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
    private String jwt;
    private Employee employee;

}
