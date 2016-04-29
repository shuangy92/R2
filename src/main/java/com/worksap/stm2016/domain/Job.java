package com.worksap.stm2016.domain;


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

    @Column(name = "title")
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "requirement", nullable = false)
    private String requirement;

    @Column(name = "hours")
    private Integer hours;
}