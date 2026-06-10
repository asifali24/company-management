package com.Company.CompanySystem.advices;


import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class ApiError {
    private HttpStatus httpStatusCode;
    private Boolean status;
    private String message;
    private List<String> subErrors;
}
