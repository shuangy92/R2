package com.worksap.stm2016.domain.workforce;


import lombok.Data;

import java.io.Serializable;

@Data
public class WorkforcePK implements Serializable {

    private static final long serialVersionUID = 1L;

    private Short year;
    private Short month;
    private Long departmentId;

    public WorkforcePK() {}

    public WorkforcePK(Short year, Short month, Long departmentId) {
        this.year = year;
        this.month = month;
        this.departmentId = departmentId;
    }
}