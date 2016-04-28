package com.worksap.stm2016.domain;


import com.worksap.stm2016.domain.message.StaffingRequest;
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
    @JoinColumn(name = "request_id")
    private StaffingRequest staffingRequest;

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