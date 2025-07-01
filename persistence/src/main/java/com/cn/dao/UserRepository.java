package com.cn.dao;

import com.cn.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author LinChen
 */
@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long>, PagingAndSortingRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
