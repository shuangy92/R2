package com.worksap.stm2016.domain.review;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.worksap.stm2016.domain.user.User;
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

    @JsonBackReference(value="flow-runs")
    @ManyToOne
    @JoinColumn(name = "review_flow_id")
    private ReviewFlow reviewFlow;

    @Column(name = "run_number")
    private Short runNumber;

    @Column(name = "note")
    private String note;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReviewType type;

    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    private User reviewer;

    public enum ReviewType {
        INTERVIEW,
        RESUME_CHECK,
        ONLINE_TEST
    }

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
                ", note='" + note + '\'' +
                ", reviewFlow=" + reviewFlow +
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