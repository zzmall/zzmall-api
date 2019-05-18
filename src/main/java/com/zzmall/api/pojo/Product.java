package com.zzmall.api.pojo;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "zzmall_product")
@DynamicUpdate
public class Product extends BaseDate {

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