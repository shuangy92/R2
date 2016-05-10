package com.worksap.stm2016.domain.job;


import com.worksap.stm2016.domain.Department;
import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.enums.PayRate;
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
    @JoinColumn(referencedColumnName = "user_id", name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", name = "last_editor_id")
    private User lastEditor;

    @Column(name = "postDate")
    private Date postDate;

    @Column(name = "lastEditDate")
    private Date lastEditDate;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @Column(name = "salary")
    private String salary;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PayRate payRate;

    @Column(name = "deadline")
    @Type(type="date")
    private Date deadline;

    @Column(name = "published", nullable = false)
    private Boolean published = false;

    /* from staffing request */

    @Column(name = "title")
    private String title; //job title

    @Column(name = "vacancies")
    private Integer vacancies;

    @Column(name = "startDate")
    @Type(type="date")
    private Date startDate; // contract start date

    @Column(name = "end_date")
    @Type(type="date")
    private Date endDate;

    @Column(name = "contract_length")
    private String contractLength;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "requirement", nullable = false)
    private String requirement;

    @Column(name = "hours")
    private Integer hours;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "location")
    private String location;
}