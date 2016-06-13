package com.worksap.stm2016.domain.calendar;

import com.worksap.stm2016.domain.user.User;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

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

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "start_time", nullable = false)
    private Date start;

    @Column(name = "end_time", nullable = false)
    private Date end;

    @Column(name = "all_day", nullable = false)
    private Boolean allDay = false;

    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", name = "user_id", nullable = false)
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
