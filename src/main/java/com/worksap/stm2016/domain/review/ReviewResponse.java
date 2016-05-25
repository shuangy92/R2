package com.worksap.stm2016.domain.review;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.worksap.stm2016.domain.recruitment.JobApplication;
import com.worksap.stm2016.domain.user.User;
import com.worksap.stm2016.enums.ReviewStatus;
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

    @ManyToOne
    @JoinColumn(name = "review_run_id")
    private ReviewRun reviewRun;

    @JsonBackReference(value="application-responses")
    @ManyToOne
    @JoinColumn(name = "job_application_id")
    private JobApplication jobApplication;

    @Column(name = "response", columnDefinition = "text")
    private String response;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReviewStatus status = ReviewStatus.REVIEWING;
}