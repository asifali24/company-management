package com.Company.CompanySystem.services;


import com.Company.CompanySystem.customExceptions.ResourceNotFoundException;
import com.Company.CompanySystem.dto.DepartmentDto;
import com.Company.CompanySystem.entities.DepartmentEntity;
import com.Company.CompanySystem.entities.EmployeeEntity;
import com.Company.CompanySystem.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServices {
    final private DepartmentRepository departmentRepository;
    final private ModelMapper modelMapper;

    public DepartmentServices(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    public DepartmentDto createDepartment(DepartmentDto newDepartment) {
        newDepartment.setIsActive(true);
        DepartmentEntity newEntity = modelMapper.map(newDepartment, DepartmentEntity.class);
        DepartmentEntity savedDepartment = departmentRepository.save(newEntity);
        return modelMapper.map(savedDepartment,DepartmentDto.class);
    }

    public List<DepartmentDto> getAllDepartments(String title) {
        List<DepartmentEntity> allDepartments = departmentRepository.findAll();
        return allDepartments.stream()
                .map(e-> modelMapper.map(e,DepartmentDto.class))
                .toList();

    }

    public DepartmentDto getDepartmentById(Long id) {
        DepartmentEntity foundDepartment = getDepartment(id);
        return modelMapper.map(foundDepartment,DepartmentDto.class);
    }


    public DepartmentDto updateDepartmentById(Long id,DepartmentDto newDepartment) {
        DepartmentEntity oldDepartment = getDepartment(id);
        modelMapper.map(newDepartment,oldDepartment);
        departmentRepository.save(oldDepartment);
        return modelMapper.map(oldDepartment,DepartmentDto.class);
    }


    public String deleteDepartmentById(Long id) {
        DepartmentEntity isDepartmentFound = getDepartment(id);
        departmentRepository.deleteById(id);
        return "Successfully deleted the Department";
    }
    private DepartmentEntity getDepartment(Long id){
       return departmentRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
    }
}
