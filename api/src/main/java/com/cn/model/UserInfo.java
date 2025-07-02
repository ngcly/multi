package com.cn.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

/**
 * @author LinChen
 */
@Getter
@Setter
public class UserInfo implements Serializable {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Boolean disabled;
    private Instant createdAt;
}
