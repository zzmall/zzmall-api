package com.zzmall.api.pojo;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "zzmall_pay_info")
@DynamicUpdate
public class PayInfo extends BaseDate {

  private Integer userId;
  private Integer orderNo;
  private Integer payPlatform;
  private String platformNumber;
  private String platformStatus;

}
