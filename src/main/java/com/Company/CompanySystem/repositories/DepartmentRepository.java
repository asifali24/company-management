package com.Company.CompanySystem.repositories;

import com.Company.CompanySystem.dto.department.query.GetAllDepartmentWithSearchAndPaginationDto;
import com.Company.CompanySystem.dto.department.query.GetAllDepartmentWithSearchAndPaginationDtoConcrete;
import com.Company.CompanySystem.entities.DepartmentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
    public List<DepartmentEntity> findByTitle(String title);
//    public List<DepartmentEntity> findByTitleOrAll(String title);

//    @Query("SELECT d FROM DepartmentEntity d WHERE :title = '' OR d.title = :title")
//    List<DepartmentEntity> findByTitleOrAll(String title);

//        @Query(value = "SELECT * FROM department_entity WHERE :title = '' OR title = :title", nativeQuery = true)
//        List<DepartmentEntity> findByTitleOrAllRaw(@Param("title") String title);

    @Query("SELECT d FROM DepartmentEntity d WHERE :title IS NULL OR :title = '' OR d.title = :title")
    List<DepartmentEntity> findByTitleOrAllRaw(@Param("title") String title);


    public List<DepartmentEntity> findByTitleContainingIgnoreCase(String title, Pageable page);

    // with custom DTO (Interface)
    @Query("SELECT d.id as id,d.title as title FROM DepartmentEntity d WHERE :title IS NULL OR :title = '' OR d.title = :title order by d.id desc")
    public List<GetAllDepartmentWithSearchAndPaginationDto> findByTitleOrAllRaw2(@Param("title") String title);

    //with concrete class GetAllDepartmentWithSearchAndPaginationDtoConcrete

    @Query("SELECT new com.Company.CompanySystem.dto.department.query.GetAllDepartmentWithSearchAndPaginationDtoConcrete(d.title) FROM DepartmentEntity d WHERE :title IS NULL OR :title = '' OR d.title = :title order by d.id ASC")
    public List<GetAllDepartmentWithSearchAndPaginationDtoConcrete> findByTitleOrAllRaw3(@Param("title") String title);
}
