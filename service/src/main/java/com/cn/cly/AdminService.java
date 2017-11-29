package com.cn.cly;

import com.cn.cly.entity.Admin;

import java.util.Optional;

public interface AdminService {

    Optional<Admin> findUserByName(String name);
}
