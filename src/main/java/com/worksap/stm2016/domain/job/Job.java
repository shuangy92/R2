package com.worksap.stm2016.domain.job;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "job")
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "requirement")
    private String requirement;

    @Column(name = "hours")
    private Integer hours;

    @OrderBy("name ASC")
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OrderBy("name ASC")
    @ManyToOne
    @JoinColumn(name = "job_category_id")
    private JobCategory jobCategory;
}