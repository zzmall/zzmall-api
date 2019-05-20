package com.zzmall.api.pojo;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Data
@Entity
@Table(name = "zzmall_category")
@DynamicUpdate
@Proxy(lazy = false)
public class Category extends BaseObject {

  private Integer parentId;
  private String name;
  private Integer status;
  private Integer sortOrder;



}
