package com.Company.CompanySystem.repositories;

import com.Company.CompanySystem.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity , Long> {
}
