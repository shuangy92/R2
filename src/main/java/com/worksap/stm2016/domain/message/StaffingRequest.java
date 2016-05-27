package com.worksap.stm2016.domain.message;

import com.worksap.stm2016.domain.job.Job;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Shuang on 4/18/2016.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "staffing_request")
public class StaffingRequest extends Request implements Serializable {

    private static final long serialVersionUID = 1L;

    @OrderBy("title ASC")
    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @Column(name = "vacancies")
    private Integer vacancies;

    @Column(name = "startDate")
    @Type(type="date")
    private Date startDate;

    @Column(name = "end_date")
    @Type(type="date")
    private Date endDate;

    @Column(name = "contract_length")
    private String contractLength;

    @Column(name = "staff_request_type")
    @Enumerated(EnumType.STRING)
    private StaffingRequestType staffingRequestType;

    public enum StaffingRequestType {
        REPLACE, NEW
    }

    @Column(name = "job_title", nullable = false)
    private String jobTitle;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "requirement", columnDefinition = "text")
    private String requirement;

    @Column(name = "hours")
    private Integer hours;
}
