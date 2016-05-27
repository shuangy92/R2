package com.worksap.stm2016.domain.user;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.worksap.stm2016.domain.job.Contract;
import com.worksap.stm2016.domain.job.Department;
import com.worksap.stm2016.enums.Role;
import com.worksap.stm2016.enums.UserStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "person")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, updatable = false)
    private Long id;

    @Email
    @Column(name = "email",unique = true)
    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

    @Transient
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(name = "active")
    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @JsonBackReference
    @OneToOne(mappedBy = "user")
    private Contract contract;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id.equals(user.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", role=" + role +
                '}';
    }
}