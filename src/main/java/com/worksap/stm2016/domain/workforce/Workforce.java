package com.worksap.stm2016.domain.workforce;


import com.worksap.stm2016.domain.job.Department;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "workforce")
@IdClass(WorkforcePK.class)
public class Workforce implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "year")
    private Short year;

    @Id
    @Column(name = "month")
    private Short month;

    @Id
    @Column(name = "department_id")
    private Long departmentId;

    @Id
    @ManyToOne
    @PrimaryKeyJoinColumn(name="department_id")
    private Department department;

    @Column(name = "count")
    private Long count;

    public Workforce() {

    }
    public Workforce(final Department department, final Short year, final Short month, final Long count) {
        this.department = department;
        this.departmentId = department.getId();
        this.year = year;
        this.month = month;
        this.count = count;
    }
}