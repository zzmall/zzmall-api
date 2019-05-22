package com.zzmall.api.pojo;


import com.zzmall.api.common.constant.OrderConstant;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Data
@Entity
@Table(name = "zzmall_order")
@DynamicUpdate
@Proxy(lazy = false)
public class Order extends BaseObject {

  private BigInteger orderNo;
  private Integer userId;
  private Integer shippingId;
  private BigDecimal payment;
  private Integer paymentType = OrderConstant.PAYMENT_TYPE_WXPAY;
  private Integer postage = 0;
  private Integer status = OrderConstant.STATUS_UNPAID;
  private Date paymentTime;
  private Date sendTime;
  private Date endTime;
  private Date closeTime;


  public Order() {

  }

  public Order(Integer userId, Integer shippingId, BigDecimal payment) {
    this.userId = userId;
    this.shippingId = shippingId;
    this.payment = payment;
  }
}
