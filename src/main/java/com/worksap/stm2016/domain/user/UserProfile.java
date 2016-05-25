package com.worksap.stm2016.domain.user;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.worksap.stm2016.domain.Country;
import com.worksap.stm2016.domain.job.Contract;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "person_profile")
public class UserProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_profile_id", nullable = false, updatable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private User user;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="contract_id")
    private Contract contract;

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
    @Type(type = "date")
    private Date birthday;

    public void setUser(User user) {
        this.user = user;
        this.id = user.getId();
    }
}