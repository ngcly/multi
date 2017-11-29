package com.cn.cly;

import com.cn.cly.dao.AdminRepository;
import com.cn.cly.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository;

    @Override
    public Optional<Admin> findUserByName(String name) {
        return Optional.ofNullable(adminRepository.findAdminByName(name));
    }
}
