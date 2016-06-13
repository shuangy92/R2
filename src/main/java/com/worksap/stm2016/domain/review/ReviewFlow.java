package com.worksap.stm2016.domain.review;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.worksap.stm2016.domain.user.User;
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
@Table(name = "review_flow")
public class ReviewFlow implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_flow_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @JsonManagedReference(value="flow-runs")
    @OneToMany(mappedBy="reviewFlow", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewRun> runs = new ArrayList<>();

    public void addRun(ReviewRun reviewRun) {
        if (reviewRun != null) {
            runs.add(reviewRun);
            reviewRun.setRunNumber((short) runs.size());
            reviewRun.setReviewFlow(this);
        }
    }
    public void removeRun(ReviewRun reviewRun) {
        if (reviewRun != null) {
            reviewRun.setReviewFlow(null);
            runs.remove(reviewRun);
        }
    }

    @Column(name = "is_template")
    private Boolean isTemplate = false;

    /* auditing */
    @OrderBy("name ASC")
    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", name = "author_id")
    @CreatedBy
    private User author;

    @Column(name = "last_modified_date")
    @LastModifiedDate
    private Date lastModifiedDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReviewFlow that = (ReviewFlow) o;

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ReviewFlow{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}