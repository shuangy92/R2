package com.worksap.stm2016.domain.review;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

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