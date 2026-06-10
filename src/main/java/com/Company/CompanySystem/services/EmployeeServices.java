package com.Company.CompanySystem.services;

import com.Company.CompanySystem.customExceptions.ResourceNotFoundException;
import com.Company.CompanySystem.dto.EmployeeDto;
import com.Company.CompanySystem.entities.EmployeeEntity;
import com.Company.CompanySystem.repositories.EmployeeRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServices {
    private final EmployeeRepository empRepo;
    private final ModelMapper modelMapper;

    private EmployeeEntity getEmployeeById(Long id){
        return empRepo.findById(id).orElseThrow(() ->new ResourceNotFoundException("Employee not found by id: "+id));
    }

    public EmployeeServices(EmployeeRepository empRepo,ModelMapper modelMapper) {
        this.empRepo = empRepo;
        this.modelMapper = modelMapper;
    }

    public EmployeeDto createEmployeeService(EmployeeDto newEmployee){
        EmployeeEntity newEmp = modelMapper.map(newEmployee,EmployeeEntity.class);
        EmployeeEntity toBeSaveEmp = empRepo.save(newEmp);
       return modelMapper.map(toBeSaveEmp ,EmployeeDto.class );
    }

    public EmployeeDto GetUserById(Long id){
        EmployeeEntity foundEmp = getEmployeeById(id);
       return modelMapper.map(foundEmp,EmployeeDto.class);
    }

    public List<EmployeeDto> getAllEmployee(){
        List<EmployeeEntity> allEmp = empRepo.findAll();
        return allEmp
                .stream()
                .map((e) -> modelMapper.map(e,EmployeeDto.class))
                .collect(Collectors.toList());
    }

    public EmployeeDto updateAllFieldsEmpById(@Valid EmployeeDto updateEmp, Long id) {
        EmployeeEntity oldEmp = getEmployeeById(id);
        updateEmp.setId(id);
        modelMapper.map(updateEmp,oldEmp);
        EmployeeEntity updatedRes = empRepo.save(oldEmp);
        return modelMapper.map(updatedRes,EmployeeDto.class);
    }

    public String deleteEmpById(Long id) {
        EmployeeEntity oldEmp = getEmployeeById(id);
        empRepo.deleteById(id);
        return "Successfully deleted";
    }
}
