package com.worksap.stm2016.domain;


import com.worksap.stm2016.domain.job.Contract;
import com.worksap.stm2016.domain.user.User;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "job_history")
public class JobHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_history_id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;

    @Column(name = "real_start_date")
    @Type(type = "date")
    private Date realStartDate;

    @Column(name = "real_end_date")
    @Type(type = "date")
    private Date realEndDate;

}