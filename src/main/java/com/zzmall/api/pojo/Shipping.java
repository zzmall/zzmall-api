package com.zzmall.api.pojo;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Entity
@Table(name = "zzmall_shipping")
@DynamicUpdate
public class Shipping extends BaseDate {

  private Integer userId;
  private String receiverName;
  private String receiverPhone;
  private String receiverMobile;
  private String receiverProvince;
  private String receiverCity;
  private String receiverDistrict;
  private String receiverAddress;
  private String receiverZip;

}
