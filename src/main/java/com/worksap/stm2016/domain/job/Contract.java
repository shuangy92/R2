package com.worksap.stm2016.domain.job;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.worksap.stm2016.domain.user.User;
import com.worksap.stm2016.enums.PayRate;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "contract")
public class Contract implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id", nullable = false, updatable = false)
    private Long id;

    @JsonManagedReference
    @OrderBy("name ASC")
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OrderBy("title ASC")
    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @Column(name = "startDate", nullable = false)
    @Type(type="date")
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    @Type(type="date")
    private Date endDate;

    @Column(name = "salary", nullable = false)
    private String salary;

    @Column(name = "pay_rate", nullable = false)
    @Enumerated(EnumType.STRING)
    private PayRate payRate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contract contract = (Contract) o;

        return id != null ? id.equals(contract.id) : contract.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", user=" + user +
                ", job=" + job +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", salary='" + salary + '\'' +
                ", payRate=" + payRate +
                '}';
    }
}