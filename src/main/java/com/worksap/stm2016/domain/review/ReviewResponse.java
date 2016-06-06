package com.worksap.stm2016.domain.review;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.worksap.stm2016.domain.recruitment.JobApplication;
import com.worksap.stm2016.domain.user.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "review_response")
public class ReviewResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_response_id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "review_run_id")
    private ReviewRun reviewRun;

    @JsonBackReference(value="application-responses")
    @ManyToOne
    @JoinColumn(name = "job_application_id")
    private JobApplication jobApplication;

    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    private User reviewer;

    @Column(name = "response", columnDefinition = "text")
    private String response;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReviewStatus status = ReviewStatus.REVIEWING;

    public enum ReviewStatus {
        REVIEWING, PASSED, FAILED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReviewResponse response = (ReviewResponse) o;

        return id != null ? id.equals(response.id) : response.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ReviewResponse{" +
                "id=" + id +
                ", reviewer=" + reviewer +
                ", jobApplication=" + jobApplication +
                ", response='" + response + '\'' +
                ", status=" + status +
                '}';
    }
}