package com.Company.CompanySystem.controllers;


import com.Company.CompanySystem.dto.EmployeeDto;
import com.Company.CompanySystem.services.EmployeeServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/employee")
public class EmployeeController {

    private final EmployeeServices empService;

    public EmployeeController(EmployeeServices empService) {
        this.empService = empService;
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> CreateEmployee(@Valid @RequestBody EmployeeDto employee){
        EmployeeDto newEmp =  empService.createEmployeeService(employee);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newEmp);
    }

    @GetMapping("/{empId}")
    public ResponseEntity<EmployeeDto> getUserById(@PathVariable(name = "empId", required = true)  Long id){
        return ResponseEntity.status(HttpStatus.OK).body(empService.GetUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(empService.getAllEmployee());
    }

    @PutMapping("/{empId}")
    public ResponseEntity<EmployeeDto> updateEmployeeById(@PathVariable(name = "empId") Long id, @Valid @RequestBody EmployeeDto updatedEmp){
        return ResponseEntity.ok(empService.updateAllFieldsEmpById(updatedEmp,id));
    }

    @DeleteMapping("/{empId}")
    public ResponseEntity<String> deleteEmpById(@PathVariable(name = "empId") Long id){
        return ResponseEntity.ok(empService.deleteEmpById(id));
    }



}
