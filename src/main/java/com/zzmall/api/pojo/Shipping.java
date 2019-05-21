package com.zzmall.api.pojo;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Data
@Entity
@Table(name = "zzmall_shipping")
@DynamicUpdate
@Proxy(lazy = false)
public class Shipping extends BaseObject {

  private Integer userId;
  private String receiverName;
  private String receiverPhone;
  private String receiverMobile;
  private String receiverProvince;
  private String receiverCity;
  private String receiverDistrict;
  private String receiverAddress;
  private String receiverZip;

  public Shipping() {
  }

  public Shipping(Integer id) {
    super(id);
  }
}
