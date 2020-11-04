package com.thoughtworks.capacity.gtb.mvc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  private Integer id;

  @NotBlank(message = "用户名不为空")
  @Pattern(regexp = "^\\w+$", message = "用户名不合法")
  @Length(min = 3, max = 10, message = "用户名不合法")
  private String username;

  @NotBlank(message = "密码不为空")
  @Length(min = 5, max = 12, message = "密码不合法")
  private String password;

  @Email(message = "邮箱地址不合法")
  private String email;
}
