package com.worksap.stm2016.domain.message;

import com.worksap.stm2016.domain.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "email")
public class Email implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "email_id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_id", referencedColumnName = "user_id")
    private User from;

    @ManyToOne
    @JoinColumn(name = "to_id", referencedColumnName = "user_id")
    private User to;

    /*@Column(name = "to")
    private String to;

    @Column(name = "to_name")
    private String toName;*/

    @Column(name = "subject")
    private String subject;

    @Column(name = "body")
    private String body;
}
