package com.worksap.stm2016.domain;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class RosterPK implements Serializable {
    private static final long serialVersionUID = 1L;
    private long shiftId;
    private long userId;
    private Date date;
}
