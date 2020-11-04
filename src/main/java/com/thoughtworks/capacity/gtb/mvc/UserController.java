package com.thoughtworks.capacity.gtb.mvc;

import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.Pattern;

@RestController
@Validated
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public void registerUser(@RequestBody User user) {
    userService.register(user);
  }

  @GetMapping("/login")
  public User login(@RequestParam(name = "username", required = true)
                      @Pattern(regexp = "^\\w+$", message = "用户名不合法")
                      @Length(min = 3, max = 10, message = "用户名不合法") String username,
                    @RequestParam(name = "password", required = true)
                      @Length(min = 5, max = 12, message = "密码不合法") String password) {
    return userService.login(username, password);
  }
}
