package com.worksap.stm2016.domain.message;

import com.worksap.stm2016.domain.user.User;
import com.worksap.stm2016.enums.Role;
import lombok.Data;
import org.json.simple.JSONObject;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "notification")
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Column(name = "read")
    private Boolean read = false;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    /*@Column(name = "item_id")
    private Long itemId;

    @Column(name = "item_note")
    private String itemNote;*/

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private NotificationType type;

    public enum NotificationType {
        //REVIEW_START, REVIEW_UPDATE, REVIEW_UPDATE_HR,
        CONTRACT_EXPIRING, CONTRACT_EXPIRED,
        PROFILE_REVIEW, INTERVIEW
    }

    /* auditing */
    @Column(name = "created_date")
    @CreatedDate
    private Date createdDate;
}
