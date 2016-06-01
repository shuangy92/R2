package com.worksap.stm2016.domain.exam;


import com.worksap.stm2016.domain.FileProfile;
import com.worksap.stm2016.domain.job.Contract;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "question")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "question")
    private String question;

    @Column(name = "options")
    private List<String> options = new ArrayList<>();

    public void addOption(String option) {
        if (option != null) {
            options.add(option);
        }
    }
    public void removeOption(String option) {
        if (option != null) {
            options.remove(option);
        }
    }

    @Column(name = "answer")
    private String answer;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private QuestionType type;

    @ManyToOne
    @JoinColumn(name = "question_category_id")
    private QuestionCategory category;

    public enum QuestionType {
        MULTIPLE_CHOICE, SHORT_ANSWER
    }
}