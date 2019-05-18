package com.zzmall.api.pojo;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "zzmall_order_item")
@DynamicUpdate
public class OrderItem extends BaseDate {

  private Integer userId;
  private Integer orderNo;
  private Integer productId;
  private String productName;
  private String productImage;
  private BigDecimal currentUnitPrice;
  private Integer quantity;
  private BigDecimal totalPrice;

}
