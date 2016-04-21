package com.worksap.stm2016.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "time_slot")
public class TimeSlot {

    @Id
    @Column(name = "slot_id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date")
    private Integer date;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @Column(name = "occupied")
    private boolean occupied = false;
}