package com.worksap.stm2016.domain.review;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.worksap.stm2016.domain.recruitment.JobApplication;
import com.worksap.stm2016.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "review_response")
public class ReviewResponse implements Comparable<ReviewResponse> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_response_id", nullable = false, updatable = false)
    private Long id;

    @JsonBackReference(value="application-responses")
    @ManyToOne
    @JoinColumn(name = "job_application_id", nullable = false)
    private JobApplication jobApplication;

    @Column(name = "run_number", nullable = false)
    private Short runNumber;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReviewType type;

    @ManyToOne
    @JoinColumn(name = "reviewer_id", nullable = false)
    private User reviewer;

    @Column(name = "response", columnDefinition = "text")
    private String response;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReviewStatus status = ReviewStatus.REVIEWING;

    @Column(name = "interview_start_time")
    private Date start;

    @Column(name = "interview_end_time")
    private Date end;

    public enum ReviewStatus {
        REVIEWING, PASSED, FAILED;
    }

    public enum ReviewType {
        PROFILE_REVIEW, INTERVIEW, ONLINE_TEST
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

    @Override
    public int compareTo(ReviewResponse another) {
        if (this.getRunNumber() < another.getRunNumber()){
            return -1;
        }else{
            return 1;
        }
    }
}