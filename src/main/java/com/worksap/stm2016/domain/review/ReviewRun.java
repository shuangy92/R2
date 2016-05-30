package com.worksap.stm2016.domain.review;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.worksap.stm2016.domain.user.User;
import com.worksap.stm2016.enums.ReviewStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "review_run")
public class ReviewRun implements Comparable<ReviewRun> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_run_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "run_number")
    private Short runNumber;

    @Column(name = "task")
    private String task;

    @Column(name = "deadline")
    private Date deadline;

    @JsonBackReference(value="flow-runs")
    @ManyToOne
    @JoinColumn(name = "review_flow_id")
    private ReviewFlow reviewFlow;

    @ManyToMany
    @JoinColumn(name = "reviewers")
    private List<User> reviewers = new ArrayList<>();

    @Transient
    private ReviewStatus status = ReviewStatus.REVIEWING;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReviewRun reviewRun = (ReviewRun) o;

        return id != null ? id.equals(reviewRun.id) : reviewRun.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ReviewRun{" +
                "id=" + id +
                ", runNumber=" + runNumber +
                ", task='" + task + '\'' +
                ", deadline=" + deadline +
                ", reviewFlow=" + reviewFlow +
                ", status=" + status +
                '}';
    }

    @Override
    public int compareTo(ReviewRun another) {
        if (this.getRunNumber()<another.getRunNumber()){
            return -1;
        }else{
            return 1;
        }
    }

}