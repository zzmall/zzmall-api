package com.zzmall.api.pojo;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "zzmall_order")
@DynamicUpdate
@Proxy(lazy = false)
public class Order extends BaseObject {

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
