package com.cercli.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequest {
    @NotNull(message = "Name is required")
    private String name;
    @NotNull(message = "Position is required")
    private String position;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    @NotNull(message = "Salary is required")
    private Float salary;
}
