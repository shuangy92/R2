package com.worksap.stm2016.domain;


import lombok.Data;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "id_number")
    private String idNumber;

    @Column(name = "passport_number")
    private String passportNumber;

    @Column(name = "driver_license_number")
    private String driverLicenseNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    /* auditing */
    @Column(name = "last_modified_date")
    @LastModifiedDate
    private Date lastModifiedDate;
}