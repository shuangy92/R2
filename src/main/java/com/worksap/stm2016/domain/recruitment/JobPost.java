package com.worksap.stm2016.domain.recruitment;


import com.worksap.stm2016.domain.job.Department;
import com.worksap.stm2016.domain.job.Job;
import com.worksap.stm2016.domain.user.User;
import com.worksap.stm2016.enums.PayRate;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "job_post")
public class JobPost implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_post_id", nullable = false, updatable = false)
    private Long id;

    @OrderBy("title ASC")
    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @Column(name = "salary", nullable = false)
    private String salary;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private PayRate payRate;

    @Column(name = "deadline")
    @Type(type="date")
    private Date deadline;

    @Column(name = "published", nullable = false)
    private Boolean published = false;

   /* @ManyToOne
    @JoinColumn(name = "review_flow_id")
    private ReviewFlow reviewFlow;*/

    /* from staffing request */

    @Column(name = "title", nullable = false)
    private String title; //job title

    @Column(name = "vacancies", nullable = false)
    private Integer vacancies;

    public void decreaseVacancies () {
        if (this.vacancies > 0) {
            --this.vacancies;
            if (this.vacancies == 0) {
                this.open = false;
            }
        }
    }

    @Column(name = "startDate", nullable = false)
    @Type(type="date")
    private Date startDate; // contract start date

    @Column(name = "end_date", nullable = false)
    @Type(type="date")
    private Date endDate;

    @Column(name = "contract_length")
    private String contractLength;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "requirement", columnDefinition = "text")
    private String requirement;

    @Column(name = "hours")
    private Integer hours;

    @OrderBy("name ASC")
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Column(name = "open", nullable = false)
    private Boolean open = true;

    /* auditing */
    @OrderBy("name ASC")
    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", name = "author_id")
    @CreatedBy
    private User author;

    @OrderBy("name ASC")
    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", name = "last_editor_id")
    @LastModifiedBy
    private User lastEditor;

    @Column(name = "created_date")
    @CreatedDate
    private Date createdDate;

    @Column(name = "last_modified_date")
    @LastModifiedDate
    private Date lastModifiedDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobPost jobPost = (JobPost) o;

        return id != null ? id.equals(jobPost.id) : jobPost.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "JobPost{" +
                "id=" + id +
                ", job=" + job +
                ", salary='" + salary + '\'' +
                ", payRate=" + payRate +
                ", deadline=" + deadline +
                ", published=" + published +
                ", title='" + title + '\'' +
                ", vacancies=" + vacancies +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", contractLength='" + contractLength + '\'' +
                ", description='" + description + '\'' +
                ", requirement='" + requirement + '\'' +
                ", hours=" + hours +
                ", open=" + open +
                '}';
    }
}