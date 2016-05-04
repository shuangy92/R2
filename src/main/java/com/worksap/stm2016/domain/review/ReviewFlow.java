package com.worksap.stm2016.domain.review;

import com.worksap.stm2016.domain.Department;
import com.worksap.stm2016.domain.Job;
import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.enums.PayRate;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "review_flow")
public class ReviewFlow implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_flow_id", nullable = false, updatable = false)
    private Long id;
}