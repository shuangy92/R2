package com.worksap.stm2016.domain.roster;

import com.worksap.stm2016.domain.user.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "shift")
public class Shift {

    @Id
    @Column(name = "shift_id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "slot_id")
    private TimeSlot timeSlot;

    @ManyToOne
    @JoinColumn(name = "roster_id")
    private Roster roster;

    @Column(name = "date")
    private Integer date;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @Column(name = "check_in_time")
    private String checkInTime;

    @Column(name = "check_out_time")
    private String checkOutTime;

    @Column(name = "customer_rate")
    private Short customerRate;
}