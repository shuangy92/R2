package com.worksap.stm2016.domain.message;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Shuang on 4/18/2016.
 */
@Data
@Entity
@Table(name = "thread")
public abstract class Thread implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "count")
    private Integer count;
}
