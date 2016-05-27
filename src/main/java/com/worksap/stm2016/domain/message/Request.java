package com.worksap.stm2016.domain.message;

import com.worksap.stm2016.domain.job.Department;
import com.worksap.stm2016.domain.user.User;
import com.worksap.stm2016.enums.RequestStatus;
import com.worksap.stm2016.enums.RequestType;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Shuang on 4/18/2016.
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "request")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Request implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "thread_id")
    private Thread thread;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "prev_id", referencedColumnName = "request_id")
    private Request prev;

    @Column(name = "title")
    private String title;

    @Column(name = "sender_message", columnDefinition = "text")
    private String senderMessage;

    @Column(name = "replier_message", columnDefinition = "text")
    private String replierMessage;

    @Column(name = "request_type")
    @Enumerated(EnumType.STRING)
    private RequestType requestType;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private RequestStatus status = RequestStatus.PENDING;

    /* auditing */
    @OrderBy("name ASC")
    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", name = "sender_id")
    @CreatedBy
    private User sender;

    @OrderBy("name ASC")
    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", name = "replier_id")
    @LastModifiedBy
    private User replier;

    @Column(name = "send_date")
    @CreatedDate
    private Date sendDate;

    @Column(name = "reply_date")
    @LastModifiedDate
    private Date replyDate;

}
