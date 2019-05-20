package com.zzmall.api.pojo;


import com.zzmall.api.common.constant.CartProductConstant;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Data
@Entity
@Table(name = "zzmall_cart")
@DynamicUpdate
@Proxy(lazy = false)
public class Cart extends BaseObject {

  private Integer userId;
  private Integer productId;
  private Integer quantity;
  private Integer checked = CartProductConstant.CHECKED_TRUE;

  public Cart() {
  }

  public Cart(Integer userId, Integer productId, Integer quantity) {
    this.userId = userId;
    this.productId = productId;
    this.quantity = quantity;
  }


}
