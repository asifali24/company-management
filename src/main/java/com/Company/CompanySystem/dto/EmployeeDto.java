package com.Company.CompanySystem.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeDto {
    private Long id;

    @NotBlank(message = "name cant be empty")
    @Size(min=2,max = 15,message = "name length should be min 3 max 15")
    private String name;

    @Max(value = 61,message = "max age is 61")
    @Min(value=18, message = "min age is 18")
    private int age;

    @NotBlank(message = "Email is required")
    @Email(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "Invalid email format")
    private String email;

    private LocalDate dateOfJoining;

    private LocalDate createdAt;
    private LocalDate updatedAt;
    private Boolean isActive;

    @NotBlank(message = "roll is required")
    private String role;
    @NotNull(message = "Salary is required")
    @Positive(message = "Salary must be greater than 0")
    private Double salary;

}
