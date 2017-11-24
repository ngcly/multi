package com.cn.cly;


import com.cn.cly.dao.UserRepository;
import com.cn.cly.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> userList() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(long userId) {
        return userRepository.findOne(userId);
    }

    @Override
    public Optional<User> findUserByName(String name) {
        return Optional.ofNullable(userRepository.findUserByName(name));
    }

}
