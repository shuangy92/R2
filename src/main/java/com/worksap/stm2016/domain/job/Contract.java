package com.worksap.stm2016.domain.job;

import com.worksap.stm2016.domain.user.User;
import com.worksap.stm2016.enums.PayRate;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "contract")
public class Contract implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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

    @Column(name = "active")
    private Boolean active = true;

}