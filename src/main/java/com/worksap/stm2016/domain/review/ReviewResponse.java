package com.worksap.stm2016.domain.review;

import com.worksap.stm2016.domain.User;
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
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "review_run_id")
    private ReviewRun reviewRun;

    @Column(name = "response")
    private String response;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReviewResponseStatus status;

    public enum ReviewResponseStatus {
        PASSED, FAILED
    }
}