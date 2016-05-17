package com.worksap.stm2016.domain.job;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.worksap.stm2016.domain.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "department")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "manager_id")
    private Long managerId;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;
}