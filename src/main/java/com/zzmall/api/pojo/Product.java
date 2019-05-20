package com.zzmall.api.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "zzmall_product")
@DynamicUpdate
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
@Proxy(lazy = false)
public class Product extends BaseObject {

  private Integer categoryId;
  private String name;
  private String subtitle;
  private String mainImage;
  private String subImages;
  private String detail;
  private BigDecimal price;
  private Integer stock;
  private Integer status;

}
