package com.worksap.stm2016.domain.message;

import com.worksap.stm2016.domain.Job;
import com.worksap.stm2016.enums.RequestType;
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
@DiscriminatorValue(value=RequestType.Values.OTHER)
@Table(name = "other_request")
public class OtherRequest extends Request implements Serializable {

    private static final long serialVersionUID = 1L;

}
