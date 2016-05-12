package com.worksap.stm2016.domain;


import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "person_profile")
public class UserProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_profile_id", nullable = false, updatable = false)
    private Long id;

    @Transient
    @Column(name = "name")
    private String name;

    @Column(name = "id_number")
    private String idNumber;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "birthday")
    @Type(type="date")
    private Date birthday;

    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;

    /* auditing */
    @Column(name = "last_modified_date")
    @LastModifiedDate
    private Date lastModifiedDate;
}