package com.Company.CompanySystem.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private Integer age;
    private LocalDate dateOfJoining;
    @JsonProperty("isActive")
    private Boolean isActive;
    private String role;
    private Double salary;
    private LocalDate createdAt;
    private LocalDate updatedAt;



    @PrePersist
    public void PrePersist(){
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }
    @PostUpdate
    public void postUpdate(){
        this.updatedAt = LocalDate.now();
    }
}
