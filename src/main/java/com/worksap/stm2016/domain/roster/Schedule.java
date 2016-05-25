package com.worksap.stm2016.domain.roster;

import com.worksap.stm2016.domain.user.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @Column(name = "schedule_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "start_date")
    private Integer startDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "timezone")
    private String timeZone;
}