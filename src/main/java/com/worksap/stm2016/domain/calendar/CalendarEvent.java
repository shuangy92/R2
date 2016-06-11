package com.worksap.stm2016.domain.calendar;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.worksap.stm2016.audit.CurrentUser;
import com.worksap.stm2016.domain.user.User;
import com.worksap.stm2016.util.DateUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.Authentication;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "calendar_event")
public class CalendarEvent {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calendar_event_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "start_time")
    private Date start;

    @Column(name = "end_time")
    private Date end;

    @Column(name = "all_day")
    private Boolean allDay = false;

    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", name = "user_id")
    private User user;

    /*@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Transient
    private Boolean editable;

    public Boolean getEditable() {
        return this.assigner.getId().equals(this.user.getId());
    }*/

    /* auditing */
    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", name = "author_id")
    @CreatedBy
    private User author;
}
