package com.cn.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.*;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

/**
 * @author LinChen
 */
@Getter
@Setter
@Table("user_info")
public class UserEntity extends BaseEntity {
    @Id
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate birthday;
    private String address;
}
