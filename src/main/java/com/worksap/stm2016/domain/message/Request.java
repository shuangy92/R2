package com.worksap.stm2016.domain.message;

import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.enums.RequestStatus;
import com.worksap.stm2016.enums.RequestType;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Shuang on 4/18/2016.
 */
@Data
@Entity
@Table(name = "request")
public class Request implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "user_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "replier_id", referencedColumnName = "user_id")
    private User replier;

    @ManyToOne
    @JoinColumn(name = "prev_id", referencedColumnName = "request_id")
    private Request prev;

    @Column(name = "title")
    private String title;

    @Column(name = "sender_message")
    private String senderMessage;

    @Column(name = "replier_message")
    private String replierMessage;

    @Column(name = "send_date")
    private Date sendDate;

    @Column(name = "reply_date")
    private Date replyDate;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private RequestType type;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private RequestStatus status = RequestStatus.PENDING;
}
