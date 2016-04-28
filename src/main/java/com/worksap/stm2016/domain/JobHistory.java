package com.worksap.stm2016.domain;


import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "employment")
public class JobHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employment_id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;

    @Column(name = "startDate")
    @Type(type="date")
    private Date startDate;

    @Column(name = "end_date")
    @Type(type="date")
    private Date endDate;


}