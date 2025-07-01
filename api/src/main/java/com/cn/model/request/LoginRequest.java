package com.cn.model.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author LinChen
 */
@Getter
@Setter
public class LoginRequest implements Serializable {
    private String username;
    private String password;
}
