package com.zzmall.api.pojo;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Data
@Entity
@Table(name = "zzmall_pay_info")
@DynamicUpdate
@Proxy(lazy = false)
public class PayInfo extends BaseObject {

  private Integer userId;
  private Integer orderNo;
  private Integer payPlatform;
  private String platformNumber;
  private String platformStatus;

}
