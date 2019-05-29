package com.zzmall.api.pojo;


import com.zzmall.api.common.constant.PayPlatformConstant;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.math.BigInteger;

@Data
@Entity
@Table(name = "zzmall_pay_info")
@DynamicUpdate
@Proxy(lazy = false)
public class PayInfo extends BaseObject {

  private Integer userId;
  private BigInteger orderNo;
  private Integer payPlatform = PayPlatformConstant.WECHAT;
  private String platformNumber;
  private String platformStatus;

}
