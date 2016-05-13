package com.worksap.stm2016.domain.message;

import com.worksap.stm2016.enums.RequestType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Shuang on 4/18/2016.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DiscriminatorValue(value=RequestType.Values.OTHER)
@Table(name = "other_request")
public class OtherRequest extends Request implements Serializable {

    private static final long serialVersionUID = 1L;

}
