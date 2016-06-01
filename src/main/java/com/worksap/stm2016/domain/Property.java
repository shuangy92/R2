package com.worksap.stm2016.domain;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "property")
public class Property implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "name", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private PropertyName name;

    @Column(name = "value", nullable = false)
    private Integer value;

    public enum PropertyName {
        contractNotifyDaysBefore, contractNotifyDayLength,
        autoAddFormerEmployee
    }
}