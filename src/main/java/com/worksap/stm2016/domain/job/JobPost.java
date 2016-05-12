package com.worksap.stm2016.domain.job;


import com.worksap.stm2016.domain.Department;
import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.enums.PayRate;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "job_post")
public class JobPost implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_post_id", nullable = false, updatable = false)
    private Long id;

    @OrderBy("title ASC")
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

    /* auditing */
    @OrderBy("name ASC")
    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", name = "author_id")
    @CreatedBy
    private User author;

    @OrderBy("name ASC")
    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", name = "last_editor_id")
    @LastModifiedBy
    private User lastEditor;

    @Column(name = "created_date")
    @CreatedDate
    private Date createdDate;

    @Column(name = "last_modified_date")
    @LastModifiedDate
    private Date lastModifiedDate;

}