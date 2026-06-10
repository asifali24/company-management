package com.Company.CompanySystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {

    private Long id;

    @NotBlank(message = "Department title is required")
    @Size(min = 3,max = 30,message = "Title should be min 3 alphabet and max 30.")
    private String title;


    private Boolean isActive;
    private LocalDateTime createdAt;
}
