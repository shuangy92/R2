package com.worksap.stm2016.domain.recruitment;

import com.worksap.stm2016.domain.User;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "job_application")
public class JobApplication implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id", nullable = false, updatable = false)
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

    public enum JobApplicationStatus {
        SAVED, SUBMITTED, PASSED, FAILED, WITHDREW
    }
}