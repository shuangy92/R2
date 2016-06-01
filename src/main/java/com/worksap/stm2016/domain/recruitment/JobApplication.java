package com.worksap.stm2016.domain.recruitment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.worksap.stm2016.domain.review.ReviewResponse;
import com.worksap.stm2016.domain.user.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "job_application")
public class JobApplication implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_application_id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_post_id")
    private JobPost jobPost;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private JobApplicationStatus status = JobApplicationStatus.SAVED;

    /* auditing */
    @OrderBy("name ASC")
    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", name = "applicant_id")
    @CreatedBy
    private User applicant;

    @Column(name = "created_date")
    @LastModifiedDate
    private Date applyDate;

    @JsonManagedReference(value="application-responses")
    @OneToMany(mappedBy="jobApplication", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewResponse> responses = new ArrayList<>();

    public void addResponse(ReviewResponse reviewResponse) {
        if (reviewResponse != null) {
            responses.add(reviewResponse);
            reviewResponse.setJobApplication(this);
        }
    }
    public void removeResponse(ReviewResponse reviewResponse) {
        if (reviewResponse != null) {
            reviewResponse.setJobApplication(null);
            responses.remove(reviewResponse);
        }
    }

    public enum JobApplicationStatus {
        SAVED, SUBMITTED, WITHDREW,
        REVIEWING, PASSED, FAILED, CLOSED,
        OFFER_SENT, OFFER_ACCEPTED, OFFER_DECLINED
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobApplication that = (JobApplication) o;

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "JobApplication{" +
                "id=" + id +
                ", status=" + status +
                ", applicant=" + applicant +
                ", applyDate=" + applyDate +
                '}';
    }
}