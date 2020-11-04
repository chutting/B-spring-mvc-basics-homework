package com.thoughtworks.capacity.gtb.mvc;

import com.thoughtworks.capacity.gtb.mvc.exception.UsernameExistedException;
import com.thoughtworks.capacity.gtb.mvc.exception.UsernameOrPasswordError;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
  private List<User> userList;

  public UserService(List<User> userList) {
    this.userList = userList;
  }

  private List<User> findUserByUsername(String username) {
    return userList.stream().filter(userMember -> userMember.getUsername().equals(username)).collect(Collectors.toList());
  }

  private Integer findLatestId() {
    return this.userList.get(this.userList.size() - 1).getId();
  }

  public void register(User user) {
    if (findUserByUsername(user.getUsername()).size() != 0) {
      throw new UsernameExistedException("用户已存在");
    }
    if (this.userList.size() == 0) {
      user.setId(1);
    } else {
      user.setId(findLatestId() + 1);
    }
    userList.add(user);
  }

  public User login(String username, String password) {
    List<User> userByUsername = findUserByUsername(username);
    if (userByUsername.size() == 0 || !userByUsername.get(0).getPassword().equals(password)) {
      throw new UsernameOrPasswordError("用户名或密码错误");
    }
    return userByUsername.get(0);
  }
}
