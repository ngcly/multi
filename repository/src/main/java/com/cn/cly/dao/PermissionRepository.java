package com.cn.cly.dao;

import com.cn.cly.entity.Permission;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

/**
 * @author chen
 * @date 2017-12-20 16:34
 */

@Repository
@Table(name="permission")
@Qualifier("permissionRepository")
public interface PermissionRepository extends JpaRepository<Permission,Long> {

    @Query("select t from Permission t where t.available='1' order by t.sort")
    List<Permission> findMenuAll();
}

