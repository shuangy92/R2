package com.worksap.stm2016.domain.review;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "review_run")
public class ReviewRun implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_run_id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "review_flow_id")
    private ReviewFlow reviewFlow;

    @Column(name = "run_number")
    private Short runNumber;

    @Column(name = "title")
    private String title;

    @Column(name = "deadline")
    private Date deadline;
}