package com.Company.CompanySystem.controllers;
import com.Company.CompanySystem.dto.DepartmentDto;
import com.Company.CompanySystem.services.DepartmentServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/v1/department")
public class DepartmentController {
    final private DepartmentServices departmentServices;
    public DepartmentController(DepartmentServices departmentServices) {
        this.departmentServices = departmentServices;
    }

    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@Valid @RequestBody DepartmentDto newDepartment){
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentServices.createDepartment(newDepartment));
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments(@RequestParam(defaultValue = "") String title){
        return ResponseEntity.ok(departmentServices.getAllDepartments(title));
    }

    @GetMapping("/{depId}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable(name = "depId") Long id){
        return ResponseEntity.ok(departmentServices.getDepartmentById(id));
    }

    @DeleteMapping("/{depId}")
    public ResponseEntity<String> deleteDepartmentById(@PathVariable(name = "depId") Long id){
        return ResponseEntity.ok(departmentServices.deleteDepartmentById(id));
    }

    @PutMapping("/{depId}")
    public ResponseEntity<DepartmentDto> updateDepartmentById(@Valid @PathVariable(name = "depId") Long id, @RequestBody DepartmentDto newDepartment){
        return ResponseEntity.ok(departmentServices.updateDepartmentById(id,newDepartment));
    }
}
