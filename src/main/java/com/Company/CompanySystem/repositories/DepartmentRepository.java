package com.Company.CompanySystem.repositories;

import com.Company.CompanySystem.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
    public List<DepartmentEntity> findByTitle(String title);
}
