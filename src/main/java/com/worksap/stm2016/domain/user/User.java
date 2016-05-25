package com.worksap.stm2016.domain.user;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.worksap.stm2016.domain.job.Department;
import com.worksap.stm2016.enums.Role;
import com.worksap.stm2016.enums.UserStatus;
import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "person")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, updatable = false)
    private Long id;

    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Transient
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(name = "active", nullable = false)
    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}