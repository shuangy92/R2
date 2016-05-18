package com.worksap.stm2016.domain.review;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.worksap.stm2016.domain.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "review_run")
public class ReviewRun implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_run_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "run_number")
    private Short runNumber;

    @Column(name = "task")
    private String task;

    @Column(name = "deadline")
    private Date deadline;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "review_flow_id")
    private ReviewFlow reviewFlow;

    @OneToMany
    @JoinColumn(name = "reviewers")
    private List<User> reviewers = new ArrayList<>();

    @Override
    public String toString() {
        return "";
    }

}