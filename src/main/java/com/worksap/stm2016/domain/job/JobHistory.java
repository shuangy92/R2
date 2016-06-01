package com.worksap.stm2016.domain.job;


import com.worksap.stm2016.domain.job.Contract;
import com.worksap.stm2016.domain.job.Job;
import com.worksap.stm2016.domain.user.User;
import com.worksap.stm2016.enums.PayRate;
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

    public JobHistory() {}

    public JobHistory(Contract contract) {
        this.user = contract.getUser();
        this.startDate = contract.getStartDate();
        this.endDate = contract.getEndDate();
        this.job = contract.getJob();
        this.salary = contract.getSalary();
        this.payRate = contract.getPayRate();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_history_id", nullable = false, updatable = false)
    private Long id;

    @OrderBy("name ASC")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OrderBy("title ASC")
    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @Column(name = "startDate")
    @Type(type="date")
    private Date startDate;

    @Column(name = "end_date")
    @Type(type="date")
    private Date endDate;

    @Column(name = "salary")
    private String salary;

    @Column(name = "pay_rate")
    @Enumerated(EnumType.STRING)
    private PayRate payRate;
}