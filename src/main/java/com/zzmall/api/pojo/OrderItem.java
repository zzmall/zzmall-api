package com.zzmall.api.pojo;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Data
@Table(name = "zzmall_order_item")
@DynamicUpdate
@Proxy(lazy = false)
public class OrderItem extends BaseObject {

  private Integer userId;
  private BigInteger orderNo;
  private Integer productId;
  private String productName;
  private String productImage;
  private BigDecimal currentUnitPrice;
  private Integer quantity;
  private BigDecimal totalPrice;

}
