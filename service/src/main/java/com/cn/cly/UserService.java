package com.cn.cly;

import com.cn.cly.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> userList();

    User getUser(long userId);

    Optional<User> findUserByName(String name);
}
