package com.worksap.stm2016.domain.roster;


import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.enums.RosterPlan;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roster")
public class Roster {

    @Id
    @Column(name = "roster_id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "user_id")
    private User author;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private RosterPlan plan;

    @Column(name = "start_date")
    private Integer startDate;

    @Column(name = "timezone")
    private String timeZone;
}