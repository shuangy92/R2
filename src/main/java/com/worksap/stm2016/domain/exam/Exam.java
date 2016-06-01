package com.worksap.stm2016.domain.exam;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "exam")
public class Exam implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_id", nullable = false, updatable = false)
    private Long id;

    @OneToMany
    @JoinColumn(name = "questions")
    private List<Question> questions = new ArrayList<>();
}