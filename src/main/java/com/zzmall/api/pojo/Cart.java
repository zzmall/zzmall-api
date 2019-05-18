package com.zzmall.api.pojo;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Entity
@Table(name = "zzmall_cart")
@DynamicUpdate
public class Cart extends BaseDate {

  private Integer userId;
  private Integer productId;
  private Integer quantity;
  private Integer checked;

}
