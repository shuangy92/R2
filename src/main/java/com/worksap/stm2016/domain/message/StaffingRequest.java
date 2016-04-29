package com.worksap.stm2016.domain.message;

import com.worksap.stm2016.domain.Job;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Shuang on 4/18/2016.
 */
@Data
@Entity
@Table(name = "staffing_request")
public class StaffingRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="request_id")
    private Long id;

    @MapsId
    @OneToOne(mappedBy = "staffingRequest")
    @JoinColumn(name = "request_id")
    private Request request;


    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @Column(name = "vacancies")
    private Integer vacancies;

    @Column(name = "startDate")
    @Type(type="date")
    private Date startDate;

    @Column(name = "end_date")
    @Type(type="date")
    private Date endDate;

    @Column(name = "deadline")
    private Date deadline;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private StaffingRequestType type;

    public enum StaffingRequestType {
        REPLACE, NEW
    }
}
