package com.worksap.stm2016.domain;


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

    @ManyToOne
    @JoinColumn(name = "manager")
    private User manager;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;
}