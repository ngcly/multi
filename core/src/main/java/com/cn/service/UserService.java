package com.cn.service;

import com.cn.dao.UserRepository;
import com.cn.model.UserInfo;
import com.cn.model.request.BasePageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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

    public Page<UserInfo> getUsers(BasePageRequest pageRequest) {
        PageRequest pageable = pageRequest.getPageRequest(PageRequest::of);
        return userRepository.findAll(pageable)
                .map(userEntity -> {
                    UserInfo userInfo = new UserInfo();
                    userInfo.setId(userEntity.getId());
                    userInfo.setUsername(userEntity.getUsername());
                    userInfo.setFirstName(userEntity.getFirstName());
                    userInfo.setLastName(userEntity.getLastName());
                    userInfo.setEmail(userEntity.getEmail());
                    userInfo.setPhone(userEntity.getPhone());
                    return userInfo;
                });
    }
}
