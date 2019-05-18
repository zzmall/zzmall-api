package com.zzmall.api.pojo;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "zzmall_order")
@DynamicUpdate
public class Order extends BaseDate {

  private Integer orderNo;
  private Integer userId;
  private Integer shippingId;
  private BigDecimal payment;
  private Integer paymentType;
  private Integer postage;
  private Integer status;
  private Date paymentTime;
  private Date sendTime;
  private Date endTime;
  private Date closeTime;

}
