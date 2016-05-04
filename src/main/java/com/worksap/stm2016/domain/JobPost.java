package com.worksap.stm2016.domain;


import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "job_post")
public class JobPost implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_post_id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @Column(name = "vacancies")
    private Integer vacancies;

    @Column(name = "startDate")
    @Type(type="date")
    private Date startDate; // contract start date

    @Column(name = "end_date")
    @Type(type="date")
    private Date endDate;

    @Column(name = "deadline")
    private Date deadline;

    @Column(name = "title")
    private String title;

    @Column(name = "salary")
    private String salary;

    @Column(name = "postDate")
    @Type(type="date")
    private Date postDate;

    @Column(name = "published", nullable = false)
    private boolean published = false;
}