package com.worksap.stm2016.domain.recruitment;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.worksap.stm2016.domain.review.ReviewResponse;
import com.worksap.stm2016.domain.review.ReviewRun;
import com.worksap.stm2016.domain.user.User;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
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
    @OneToMany(mappedBy="jobApplication", cascade = {CascadeType.ALL})
    private List<ReviewResponse> responses = new ArrayList<>();

    public void addResponse(ReviewResponse reviewResponse) {
        if (reviewResponse != null) {
            responses.add(reviewResponse);
            reviewResponse.setJobApplication(this);
        }
    }
    public void removeResponse(ReviewResponse reviewResponse) {
        if (reviewResponse != null) {
            responses.remove(reviewResponse);
        }
    }

    public enum JobApplicationStatus {
        SAVED, SUBMITTED, REVIEWING, PASSED, FAILED, WITHDREW
    }
}