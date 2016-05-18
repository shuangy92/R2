package com.worksap.stm2016.domain.review;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.worksap.stm2016.domain.User;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
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
@Table(name = "review_flow")
public class ReviewFlow implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_flow_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @JsonManagedReference
    @OneToMany(mappedBy="reviewFlow")
    private List<ReviewRun> runs = new ArrayList<>();

    public void addRun(ReviewRun reviewRun) {
        if (reviewRun != null) {
            runs.add(reviewRun);
            reviewRun.setReviewFlow(this);
        }
    }
    public void removeRun(ReviewRun reviewRun) {
        if (reviewRun != null) {
            runs.remove(reviewRun);
        }
    }

    @Override
    public String toString() {
        return "";
    }

    /* auditing */
    @OrderBy("name ASC")
    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", name = "author_id")
    @CreatedBy
    private User author;

    @Column(name = "last_modified_date")
    @LastModifiedDate
    private Date lastModifiedDate;

}