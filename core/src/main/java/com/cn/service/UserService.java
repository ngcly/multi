package com.cn.service;

import cn.hutool.core.bean.BeanUtil;
import com.cn.dao.UserRepository;
import com.cn.entity.UserEntity;
import com.cn.model.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

/**
 * @author LinChen
 */
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).map(user ->
                User.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .roles("USER") // 默认角色为用户
                        .accountExpired(false)
                        .accountLocked(false)
                        .credentialsExpired(false)
                        .disabled(false)
                        .build()
        ).orElseThrow();
    }

    @Transactional(readOnly = true)
    public Page<UserInfo> findUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userEntity -> {
                    UserInfo userInfo = new UserInfo();
                    userInfo.setId(userEntity.getId());
                    userInfo.setUsername(userEntity.getUsername());
                    userInfo.setFirstName(userEntity.getFirstName());
                    userInfo.setLastName(userEntity.getLastName());
                    userInfo.setEmail(userEntity.getEmail());
                    userInfo.setPhone(userEntity.getPhone());
                    userInfo.setCreatedAt(userEntity.getCreatedAt());
                    return userInfo;
                });
    }

    @Transactional(readOnly = true)
    public Optional<UserInfo> findById(Long id) {
        return userRepository.findById(id).map(userEntity -> {
            UserInfo userInfo = new UserInfo();
            BeanUtils.copyProperties(userEntity, userInfo);
            return userInfo;
        });
    }

    @Transactional(rollbackFor = Exception.class)
    public UserInfo saveUserInfo(UserInfo user) {
        UserEntity userEntity;
        if(Objects.nonNull(user.getId())) {
            userEntity = userRepository.findById(user.getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
        } else {
            userEntity = new UserEntity();
        }
        BeanUtil.copyProperties(user, userEntity);
        userRepository.save(userEntity);
        BeanUtils.copyProperties(userEntity, user);
        return user;
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public boolean isUsernameExists(String username, Long excludeId) {
        if (excludeId != null) {
            return userRepository.existsByUsernameAndIdNot(username, excludeId);
        }
        return userRepository.existsByUsername(username);
    }

    public boolean isEmailExists(String email, Long excludeId) {
        if (excludeId != null) {
            return userRepository.existsByEmailAndIdNot(email, excludeId);
        }
        return userRepository.existsByEmail(email);
    }
}
