package com.zzmall.api.pojo;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Entity
@Table(name = "zzmall_category")
@DynamicUpdate
public class Category extends BaseDate {

  private Integer parentId;
  private String name;
  private Integer status;
  private Integer sortOrder;



}
