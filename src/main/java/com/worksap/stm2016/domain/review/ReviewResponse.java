package com.worksap.stm2016.domain.review;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.domain.recruitment.JobPost;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "review_response")
public class ReviewResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_response_id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    private User reviewer;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "review_run_id")
    private ReviewRun reviewRun;

    @ManyToOne
    @JoinColumn(name = "job_post_id")
    private JobPost jobPost;

    @Column(name = "response")
    private String response;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReviewResponseStatus status;

    public enum ReviewResponseStatus {
        PASSED, FAILED
    }
}