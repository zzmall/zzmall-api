package com.zzmall.api.pojo;


import com.zzmall.api.common.constant.ApiConstant;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Data
@Entity
@Table(name = "zzmall_user")
@DynamicUpdate
@Proxy(lazy = false)
public class User extends BaseObject {

  private String username;
  private String password;
  private String email;
  private String phone;
  private String question;
  private String answer;
  private Integer role = ApiConstant.GENERAL_USER;

}
