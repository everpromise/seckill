package com.songjz.seckill.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
@TableName("user")
public class User implements Serializable {


  private Long id;

  private String phone;

  private String nickname;

  /**
   * MD5(MD5(pass明文+固定salt)+salt)
   */
  private String password;

  private String slat;

  /**
   * 头像
   */
  private String head;

  /**
   * 注册时间
   */
  private Date registerDate;

  /**
   * 最后一次登录事件
   */
  private Date lastLoginDate;

  /**
   * 登录次数
   */
  private Integer loginCount;


}
