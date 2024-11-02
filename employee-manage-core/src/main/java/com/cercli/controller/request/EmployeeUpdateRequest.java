package com.cercli.controller.request;

import com.cercli.annotation.AtLeastOneFieldRequired;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AtLeastOneFieldRequired(fields = {"name", "position", "email", "salary"}, message = "At least one of field1, field2, field3, or field4 must be provided")
public class EmployeeUpdateRequest {
    private String name;
    private String position;
    @Email(message = "Invalid email format")
    private String email;
    private Float salary;
}
