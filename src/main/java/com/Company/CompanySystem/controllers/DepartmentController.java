package com.Company.CompanySystem.controllers;
import com.Company.CompanySystem.dto.DepartmentDto;
import com.Company.CompanySystem.dto.department.query.GetAllDepartmentWithSearchAndPaginationDto;
import com.Company.CompanySystem.dto.department.query.GetAllDepartmentWithSearchAndPaginationDtoConcrete;
import com.Company.CompanySystem.entities.DepartmentEntity;
import com.Company.CompanySystem.repositories.DepartmentRepository;
import com.Company.CompanySystem.services.DepartmentServices;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/v1/department")
public class DepartmentController {
    final private DepartmentServices departmentServices;
    final private DepartmentRepository departmentRepository;
    public DepartmentController(DepartmentServices departmentServices,DepartmentRepository departmentRepository) {
        this.departmentServices = departmentServices;
        this.departmentRepository = departmentRepository;
    }

    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@Valid @RequestBody DepartmentDto newDepartment){
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentServices.createDepartment(newDepartment));
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments(
            @RequestParam(defaultValue = "",required = false) String title,
            @RequestParam(defaultValue ="0" ,required=false ) int page,
            @RequestParam(defaultValue = "10", required = false ) int size,
            @RequestParam(defaultValue = "",required = false) String sortBy,
            @RequestParam(defaultValue = "desc",required = false) String sortDirection

    ){
        return ResponseEntity.ok(departmentServices.getAllDepartments(title,page,size,sortBy,sortDirection));
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



//    for testing ...........................

    // 1
    @GetMapping("/test")
    public List<DepartmentEntity> getAllProducts(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "0") Integer pageNumber) {

        return departmentRepository.findByTitleContainingIgnoreCase(
                title,
                PageRequest.of(pageNumber, 10, Sort.by(sortBy))
        );
    }


    //2

    @GetMapping("/test-2")
    public List<GetAllDepartmentWithSearchAndPaginationDto> getAllProducts2(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "0") Integer pageNumber) {

        return departmentRepository.findByTitleOrAllRaw2(title);
    }

    //3

    @GetMapping("/test-3")
    public List<GetAllDepartmentWithSearchAndPaginationDtoConcrete> getAllProducts2(
            @RequestParam(defaultValue = "") String title) {

        return departmentRepository.findByTitleOrAllRaw3(title);
    }

}
