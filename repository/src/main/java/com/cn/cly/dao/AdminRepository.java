package com.cn.cly.dao;

import com.cn.cly.entity.Admin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name="admin")
@Qualifier("adminRepository")
public interface AdminRepository extends JpaRepository<Admin,Long> {

    @Query("select t from Admin t where t.username=:name")
    Admin findAdminByName(@Param("name") String name);
}
