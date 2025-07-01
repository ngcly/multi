package com.cn.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.*;

import java.time.Instant;

/**
 * @author LinChen
 */
@Getter
@Setter
public class BaseEntity {
    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;
    @CreatedBy
    private String createdBy;
    @LastModifiedBy
    private String updatedBy;
    @Version
    private Long version;
}
